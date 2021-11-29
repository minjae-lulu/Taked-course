
package UniRV64I
import chisel3._
import chisel3.util._
import chisel3.iotesters.PeekPokeTester



class Trace() extends Bundle {
  val pc = UInt(64.W)
  val fetched_insn = UInt(32.W)
  val wb_insn = UInt(32.W)
  val write = Bool()
  val read = Bool()
  val addr = UInt(64.W)
  val wdata = UInt(64.W)
  val rdata = UInt(64.W)
  val halted = Bool()
}


class BaseSystem(prog: Array[String] = Array("x13", "x80", "x30", "x00")) extends Module {
  val io = IO(new Bundle{
    val trace = Output(new Trace())
  })

  // Submodules
  val core = Module(new Core())
  val imem = Module(new InstructionROM(prog))
  val dmem = Module(new ReadWriteMem())
  
  // Connect instruction memory
  imem.io.addr := core.io.imem_addr
  core.io.imem_insn := imem.io.dataOut  

  dmem.io.addr := core.io.dmem_addr
  dmem.io.dataIn := core.io.dmem_wdata
  dmem.io.write := core.io.dmem_write
  dmem.io.read := core.io.dmem_read

  core.io.dmem_rdata := dmem.io.dataOut



  // Connect data memory

  // Trace interface
  val reg_dmem_read = RegNext(core.io.dmem_read)
  io.trace.pc := core.io.imem_addr
  io.trace.fetched_insn := imem.io.dataOut
  io.trace.wb_insn := core.io.wb_insn
  io.trace.wdata := Mux(core.io.dmem_write, core.io.dmem_wdata, 0.U)
  io.trace.addr := Mux(core.io.dmem_write || core.io.dmem_read, core.io.dmem_addr, 0.U)
  io.trace.rdata := Mux(reg_dmem_read, dmem.io.dataOut, 0.U)
  io.trace.write := core.io.dmem_write
  io.trace.read := core.io.dmem_read
  io.trace.halted := core.io.halted
  

}


// https://www.chisel-lang.org/chisel3/docs/explanations/memories.html
class ReadWriteMem(size: Int = 1024) extends Module {
  val width: Int = 64
  val io = IO(new Bundle {
    val read = Input(Bool())
    val write = Input(Bool())
    val addr = Input(UInt(10.W))
    val dataIn = Input(UInt(width.W))
    val dataOut = Output(UInt(width.W))
  })
  
  val mem = SyncReadMem(size, UInt(width.W))
  val const = VecInit.tabulate(1024)((x: Int) => x.U(64.W))
  val started = RegInit(true.B)
  started := false.B
  val reg_addr_read = RegNext(Mux(io.read && !started, io.addr, 0.U), init = 0.U)
  
  when(io.write) {
    mem.write(io.addr, io.dataIn)
  }
  val memOut = Wire(UInt())
  memOut := mem.read(io.addr, io.read)
  
  
  
  io.dataOut := Mux(started, 0.U, (Mux(reg_addr_read(9) === 1.U, Cat(0.U(54.W), reg_addr_read), memOut)))
  
  
}


/*
Assume aligned access for 4 bytes
*/
class InstructionROM(inits: Array[String]) extends Module {
  val width = 32.W
  val io = IO(new Bundle {
    val addr = Input(UInt(width))
    val dataOut = Output(UInt(width))
  })
  
  val size = inits.length.U(width)
  val nop = "x53".U
  
  
  val v = RegInit(
  VecInit.tabulate(inits.length)((x: Int) => inits(x).U(8.W))
  )
  io.dataOut := Mux(io.addr < size,
  Cat(v(io.addr + 3.U), v(io.addr + 2.U), v(io.addr + 1.U), v(io.addr)),
  nop
  )
  
}

