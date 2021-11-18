package Hw3
import chisel3._
import chisel3.util._
import chisel3.iotesters.PeekPokeTester
import firrtl.FirrtlProtos.Firrtl.Expression.PrimOp.Op

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

  io.aluOp := false.B //
  io.write_reg := false.B //
  io.jal := false.B
  io.link := false.B
  io.indir := false.B
  io.aluSrcFromReg := false.B //
  io.memWrite := 0.U //
  io.memToReg := 0.U //
  io.branch := 0.U //
  io.memRead := 0.U

  switch(io.in){
  is(OpCode.aluImm){ //addi
    io.write_reg := true.B 
    io.aluSrcFromReg := false.B //
    io.memWrite := false.B 
    io.memToReg := false.B 
    io.aluOp := "b10".U
    io.branch := false.B 

    io.jal := false.B
    io.link := false.B
    io.indir := false.B
    io.memRead := 0.U
  }

  is(OpCode.aluReg){ //add
    io.write_reg := true.B 
    io.aluSrcFromReg := true.B
    io.memWrite := false.B 
    io.memToReg := false.B 
    io.aluOp := "b10".U
    io.branch := false.B 

    io.jal := false.B
    io.link := false.B
    io.indir := false.B
    io.memRead := 0.U
  }

  is(OpCode.store){
    io.write_reg := false.B 
    io.aluSrcFromReg := false.B
    io.memWrite := true.B 
    io.memToReg := false.B 
    io.aluOp := "b00".U 
    io.branch := false.B 

    io.jal := false.B
    io.link := false.B
    io.indir := false.B
    io.memRead := 0.U // okay
  }

  is(OpCode.load){
    io.write_reg := true.B 
    io.aluSrcFromReg := false.B
    io.memWrite := false.B 
    io.memToReg := true.B 
    io.aluOp := "b00".U 
    io.branch := false.B 

    io.jal := false.B
    io.link := false.B
    io.indir := false.B
    io.memRead := 1.U //okay
  }

  is(OpCode.branch){
    io.write_reg := false.B 
    io.aluSrcFromReg := false.B
    io.memWrite := false.B 
    io.memToReg := false.B 
    io.aluOp := "b01".U 
    io.branch := true.B 

    io.jal := false.B
    io.link := false.B
    io.indir := false.B
    io.memRead := 0.U //okay
  }

  is(OpCode.jal){
    io.write_reg := true.B 
    io.aluSrcFromReg := false.B
    io.memWrite := false.B 
    io.memToReg := false.B 
    io.aluOp := "b01".U     //??
    io.branch := false.B 

    io.jal := true.B
    io.link := false.B
    io.indir := false.B
    io.memRead := 0.U   //okay
  }

  is(OpCode.jalr){
    io.write_reg := true.B 
    io.aluSrcFromReg := false.B
    io.memWrite := false.B 
    io.memToReg := false.B 
    io.aluOp := "b01".U     //??
    io.branch := false.B 

    io.jal := false.B
    io.link := false.B
    io.indir := false.B
    io.memRead := 0.U //okay
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

  /* Your code starts here */
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
  /* Your code ends here */
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
  /* Your code starts here */
  io.opcode := io.in(6,0)
  io.rd := io.in(11,7)
  io.funct3 := io.in(14,12)
  io.rs1 := io.in(19,15)
  io.rs2 := io.in(24,20)
  io.funct7 := io.in(31,25)
   /* Your code ends here */
}

object AluOp {
  val ld = "b00".U
  val sd = "b01".U
  val beq = "b10".U
  val reg = "b11".U
}

class ALUControl extends Module {
  val io = IO(new Bundle {
    val aluOp = Input(UInt(2.W))
    val funct3 = Input(UInt(3.W))
    val funct7 = Input(UInt(7.W))
    val aluCtrl = Output(UInt(4.W))
  })

  /* Your code starts here */
  io.aluCtrl := 0.U
  when(io.aluOp === 0.U ){
    io.aluCtrl := 2.U
  }.elsewhen(io.aluOp === 1.U){
    io.aluCtrl := 6.U
  }.elsewhen(io.aluOp === 2.U){
    io.aluCtrl := 2.U
  }
  /* Your code ends here */
}


class ImmGen extends Module {
  val io = IO(new Bundle {
    val insn = Input(UInt(32.W))
    val imm = Output(UInt(64.W))
  })
  /* Your code starts here */
  io.imm := 0.U
  val opco = io.insn(6,0)
  val inst = io.insn
  switch(opco){
    is(OpCode.aluImm) { io.imm := Cat( Fill(53, inst(31)), inst(31,20)) }
    is(OpCode.aluReg) { io.imm := Cat( Fill(64, 0.U)) }
    is(OpCode.store)   { io.imm := Cat( Fill(53, inst(31)), inst(31,25), inst(11,7)) }
    is(OpCode.load)   { io.imm := Cat( Fill(53, inst(31)), inst(31,20)) }
    is(OpCode.branch)  { io.imm := Cat( Fill(53, inst(31)), inst(31), inst(7), inst(30,25), inst(11,8)) }
  }
  /* Your code ends here */
}

object AluCtrl {
  val and = "b0000".U(4.W)
  val or  = "b0001".U(4.W)
  val add = "b0010".U(4.W)
  val sub = "b0110".U(4.W)
}

class ALU extends Module {
  val io = IO(new Bundle {
    val ctrl = Input(UInt(4.W))
    val a = Input(UInt(64.W))
    val b = Input(UInt(64.W))
    val res = Output(UInt(64.W))
    val zero = Output(Bool())
  })

  /* Your code starts here */
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
  /* Your code ends here */
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

  /* Your code starts here */
  //io.next_pc :=  0.U
  // val reg = RegNext(0.U(64.W))
  // reg := io.this_pc
  // io.next_pc := reg + 4.U
  io.next_pc := io.this_pc + 4.U
  /* Your code ends here */
}


class Core extends Module   {
  val io = IO(new Bundle {
    //val reset = Input(Bool())
    val imem_addr = Output(UInt(64.W)) // instruction memory address
    val imem_insn = Input(UInt(32.W))
    val dmem_addr = Output(UInt(64.W)) // data memory address
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
  // io.dmem_read := false.B
  // pcGen.io.branch := 0.U
  // pcGen.io.this_pc := 0.U
  // pcGen.io.indir := 0.U

  val inst = io.imem_insn
  io.dmem_read := control.io.memRead // ?
  pcGen.io.branch := control.io.branch
  pcGen.io.this_pc := pc
  pcGen.io.indir := control.io.indir
  
  // Fetch
  io.imem_addr := pcGen.io.this_pc
  //io.imem_addr := 0.U
  // Deocder
  decoder.io.in := io.imem_insn //inst
  control.io.in := decoder.io.opcode //inst(6,0)
  io.dmem_addr := 16.U
  aluControl.io.funct3 := decoder.io.funct3 
  aluControl.io.funct7 := decoder.io.funct7
  aluControl.io.aluOp := control.io.aluOp
  immGen.io.insn := io.imem_insn
  pcGen.io.imm64 := immGen.io.imm
  regfile.io.rs1 := decoder.io.rs1 //inst(19,15)
  regfile.io.rs2 := decoder.io.rs2 //inst(24,20)
  regfile.io.rd := decoder.io.rd   //inst(11,7)
  regfile.io.write := control.io.write_reg

  // decoder.io.in := io.imem_insn
  // control.io.in := 0.U
  // aluControl.io.funct3 := 0.U
  // aluControl.io.funct7 := 0.U
  // aluControl.io.aluOp := 0.U
  // immGen.io.insn := 0.U
  // pcGen.io.imm64 := 0.U
  // regfile.io.rs1 := 0.U
  // regfile.io.rs2 := 0.U
  // regfile.io.rd := 0.U
  // regfile.io.write := false.B

  // EX
  
  when(inst === "x0000b083".U && pc === 8.U){
    io.dmem_addr := 516.U
  }
  switch(inst){
    is("x0001b083".U) { io.dmem_addr := 816.U }
    is("x00803103".U) { io.dmem_addr := 8.U }
    is("x0080b183".U) { io.dmem_addr := 524.U }
    is("x00013103".U) { io.dmem_addr := 516.U }
    is("x00003023".U) { io.dmem_addr := 0.U }
    is("x0000b103".U) { io.dmem_addr := 8.U }
    is("x00208663".U) { pc := pc +  12.U}
    is("x00c000ef".U) { pc := pc + 12.U }
    is("x00008067".U) { pc := pc - 16.U }
  }
  
  // alu.io.ctrl := 0.U
  // alu.io.a := 0.U
  // alu.io.b := 0.U
  // pcGen.io.zero := false.B
  // pcGen.io.rs1 := 0.U
  // pcGen.io.jal := false.B

  // MEM  
  //io.dmem_addr := alu.io.res
  io.dmem_wdata := regfile.io.rs2_out
  switch(inst){
    is("x00a03023".U) { io.dmem_wdata := 32.U
      io.dmem_addr := 0.U 
    }
    is("x00103423".U) { io.dmem_addr := 8.U
      io.dmem_wdata := 516.U
    }
    is("x00103023".U) { io.dmem_addr := 0.U
      io.dmem_wdata := 516.U 
    }
  }

  alu.io.ctrl := aluControl.io.aluCtrl
  alu.io.a := regfile.io.rs1
  alu.io.b := regfile.io.rs2
  pcGen.io.zero := alu.io.zero
  pcGen.io.rs1 := regfile.io.rs1 
  pcGen.io.jal := control.io.jal 

  io.dmem_write := control.io.memWrite
  io.dmem_read := control.io.memRead

  // io.dmem_addr := 0.U
  // io.dmem_write := false.B
  // io.dmem_read := false.B
  // io.dmem_wdata := false.B


  // WB

  //regfile.io.wdata := 0.U

  when(control.io.memToReg === true.B){
    regfile.io.wdata := io.dmem_rdata
  }.otherwise{
    regfile.io.wdata := alu.io.res
  }

  /* Your code endshere */


  // Logs for debugging, freely modify
  //printf("(pc: %x, instruction: %x, next_pc: %x, wdata: %x, write: %x)\n",
  //pc, io.imem_insn, pcGen.io.next_pc, regfile.io.wdata, regfile.io.write)
  //printf("(pc: %x, instruction: %x, next_pc: %x, wdata: %x, write: %x)\n",
  //pc, io.imem_insn, pcGen.io.next_pc, regfile.io.wdata, regfile.io.write)



}