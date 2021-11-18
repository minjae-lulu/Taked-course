package Hw3
import chisel3._
import chisel3.util._
import chisel3.iotesters.PeekPokeTester
import firrtl.FirrtlProtos.Firrtl.Expression.PrimOp.Op
object AluCtrl {
  val and = "b0000".U(4.W)
  val or  = "b0001".U(4.W)
  val add = "b0010".U(4.W)
  val sub = "b0110".U(4.W)
}
object AluOp {
  val ld = "b00".U
  val sd = "b01".U
  val beq = "b10".U
  val reg = "b11".U
}

object OpCode {
  val aluImm   = "b0010011".U // addi
  val aluReg   = "b0110011".U // add
  val store    = "b0100011".U //sd
  val load     = "b0000011".U //ld
  val branch   = "b1100011".U //beq
  val jal      = "b1101111".U
  val jalr     = "b1100111".U
}

class Control extends Module {
  val io = IO(new Bundle{
    val in = Input(UInt(7.W))
    val write_reg = Output(Bool())
    val aluSrcFromReg = Output(Bool())
    val memWrite = Output(Bool())
    val memRead = Output(Bool())
    val memToReg = Output(Bool())
    val aluOp = Output(UInt(2.W))
    val branch = Output(Bool())
    val link = Output(Bool())
    val jal = Output(Bool())
    val indir = Output(Bool())
  })

  io.aluOp := false.B
  io.write_reg := false.B
  io.jal := false.B
  io.link := false.B
  io.indir := false.B
  io.aluSrcFromReg := false.B
  io.memWrite := 0.U
  io.memToReg := 0.U
  io.branch := 0.U
  io.memRead := 0.U


  switch(io.in){
  is(OpCode.aluImm){
    io.write_reg := true.B 
    io.aluSrcFromReg := false.B //
    io.memWrite := false.B 
    io.memToReg := false.B 
    io.aluOp := "b10".U
    io.branch := false.B 
  }

  is(OpCode.aluReg){
    io.write_reg := true.B 
    io.aluSrcFromReg := true.B
    io.memWrite := false.B 
    io.memToReg := false.B 
    io.aluOp := "b10".U
    io.branch := false.B 
  }

  is(OpCode.store){
    io.write_reg := false.B 
    io.aluSrcFromReg := false.B
    io.memWrite := true.B 
    io.memToReg := false.B 
    io.aluOp := "b00".U 
    io.branch := false.B 
  }

  is(OpCode.load){
    io.write_reg := true.B 
    io.aluSrcFromReg := false.B
    io.memWrite := false.B 
    io.memToReg := true.B 
    io.aluOp := "b00".U 
    io.branch := false.B 
  }

  is(OpCode.branch){
    io.write_reg := false.B 
    io.aluSrcFromReg := false.B
    io.memWrite := false.B 
    io.memToReg := false.B 
    io.aluOp := "b01".U 
    io.branch := true.B 
  }
}

}

class RegFile extends Module {
  val io = IO(new Bundle {
    val rd = Input(UInt(5.W))
    val rs1 = Input(UInt(5.W))
    val rs2 = Input(UInt(5.W))
    val write = Input(Bool())
    val wdata = Input(UInt(64.W))
    val rs1_out= Output(UInt(64.W))
    val rs2_out = Output(UInt(64.W))
  })

  io.rs1_out := 0.U
  io.rs2_out := 0.U

  val registers = Reg(Vec(64,UInt(64.W)))
  registers(0) := 0.U
  io.rs1_out := registers(io.rs1)
  io.rs2_out := registers(io.rs2)

  when(io.write === true.B){
    when(io.rd === "b00000".U){
      registers(io.rd) := 0.U
    }.otherwise{
      registers(io.rd) := io.wdata
    }
  }

}


class Decoder extends Module {
  val io = IO(new Bundle {
    val in = Input(UInt(32.W))
    val opcode = Output(UInt(7.W))
    val rd = Output(UInt(5.W))
    val funct3 = Output(UInt(3.W))
    val funct7 = Output(UInt(7.W))
    val rs1 = Output(UInt(5.W))
    val rs2 = Output(UInt(5.W))
  })

  io.opcode := io.in(6,0)
  io.rd := io.in(11,7)
  io.funct3 := io.in(14,12)
  io.rs1 := io.in(19,15)
  io.rs2 := io.in(24,20)
  io.funct7 := io.in(31,25)

}
class ALUControl extends Module {
  val io = IO(new Bundle {
    val aluOp = Input(UInt(2.W))
    val funct3 = Input(UInt(3.W))
    val funct7 = Input(UInt(7.W))
    val aluCtrl = Output(UInt(4.W))
  })

  io.aluCtrl := 0.U

  when(io.aluOp === 0.U ){
    io.aluCtrl := 2.U
  }.elsewhen(io.aluOp === 1.U){
    io.aluCtrl := 6.U
  }.elsewhen(io.aluOp === 2.U){
    io.aluCtrl := 2.U
  }

}

class ImmGen extends Module {
  val io = IO(new Bundle {
    val insn = Input(UInt(32.W))
    val imm = Output(UInt(64.W))
  })

  io.imm := 0.U
  val opco = io.insn(6,0)
  val inst = io.insn

  switch(opco){
    is(OpCode.aluImm) { io.imm := Cat( Fill(53, inst(31)), inst(31,20)) }
    //is(OpCode.aluReg)
    is(OpCode.store)   { io.imm := Cat( Fill(53, inst(31)), inst(31,25), inst(11,7)) }
    is(OpCode.load)   { io.imm := Cat( Fill(53, inst(31)), inst(31,20)) }
    is(OpCode.branch)  { io.imm := Cat( Fill(53, inst(31)), inst(31), inst(7), inst(30,25), inst(11,8)) }
  }
}

class ALU extends Module {
  val io = IO(new Bundle {
    val ctrl = Input(UInt(4.W))
    val a = Input(UInt(64.W))
    val b = Input(UInt(64.W))
    val res = Output(UInt(64.W))
    val zero = Output(Bool())
  })

  io.res := 0.U
  io.zero := 0.U

  when(io.a >= io.b && io.a <= io.b){
    io.zero := true.B
  }.otherwise{
    io.zero := false.B
  }
  switch(io.ctrl){
    is(AluCtrl.and) {io.res := io.a & io.b}
    is(AluCtrl.or)  {io.res := io.a | io.b}
    is(AluCtrl.add) {io.res := io.a + io.b}
    is(AluCtrl.sub) {io.res := io.a - io.b}
  }

}

class PCGen extends Module {
  val io = IO(new Bundle{
    val this_pc = Input(UInt(64.W))
    val branch = Input(Bool())
    val jal = Input(Bool())
    val indir = Input(Bool())
    val zero = Input(Bool())
    val rs1  = Input(UInt(64.W))
    val next_pc = Output(UInt(64.W))
    val imm64 = Input(UInt(64.W))
  })
  io.next_pc :=  0.U
}


class Core extends Module   {
  val io = IO(new Bundle {
    //val reset = Input(Bool())
    val imem_addr = Output(UInt(64.W))
    val imem_insn = Input(UInt(32.W))
    val dmem_addr = Output(UInt(64.W))
    val dmem_write = Output(Bool())
    val dmem_read = Output(Bool())
    val dmem_wdata = Output(UInt(64.W))
    val dmem_rdata = Input(UInt(64.W))

    val halted = Output(Bool())

  })

  /* Support for halt, don't touch start */
  val started = RegInit(false.B)
  started := true.B
  val halted = RegInit(false.B)
  when(io.imem_insn === "x00005073".U) {
    //printf("halting\n")
    halted := true.B
  }.otherwise {
    halted := halted
  }
  io.halted := halted
  /* Support for halt, don't touch end */

  /* Modules, don't touch start */

  val pc = RegInit(0.U(64.W))
  val control = Module(new Control())
  val pcGen = Module(new PCGen())
  val decoder = Module(new Decoder())
  val aluControl = Module(new ALUControl())
  val regfile = Module(new RegFile())
  val alu = Module(new ALU())
  val immGen = Module(new ImmGen())
  pc := Mux(started, pcGen.io.next_pc, pc)

  /* Modules, don't touch end */

  /* Your code starts here */





  io.dmem_read := false.B

  pcGen.io.branch := 0.U
  pcGen.io.this_pc := 0.U
  pcGen.io.indir := 0.U
  
  // Fetch

  
  
  
  io.imem_addr := 0.U
  
  

  // Deocder
  
  
  decoder.io.in := io.imem_insn
  control.io.in := 0.U
  
  aluControl.io.funct3 := 0.U
  aluControl.io.funct7 := 0.U
  aluControl.io.aluOp := 0.U

  
  immGen.io.insn := 0.U
  pcGen.io.imm64 := 0.U

  
  regfile.io.rs1 := 0.U
  regfile.io.rs2 := 0.U
  regfile.io.rd := 0.U
  
  regfile.io.write := false.B
  

  


  // EX
  
  alu.io.ctrl := 0.U
  alu.io.a := 0.U
  alu.io.b := 0.U

  
  pcGen.io.zero := false.B
  pcGen.io.rs1 := 0.U
  pcGen.io.jal := false.B

  // MEM

  io.dmem_addr := 0.U
  io.dmem_write := false.B
  io.dmem_read := false.B
  io.dmem_wdata := false.B



  


  // WB

  regfile.io.wdata := 0.U

  /* Your code endshere */

  // Logs for debugging, freely modify
  printf("(pc: %x, instruction: %x, next_pc: %x, wdata: %x, write: %x)\n",
  pc, io.imem_insn, pcGen.io.next_pc, regfile.io.wdata, regfile.io.write)

}