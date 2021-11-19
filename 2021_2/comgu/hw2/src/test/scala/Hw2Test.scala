/*
 * Homework 2, CSE261 Computer Architecture 
 * 2021 Fall
 * UNIST
 * Hyungon Moon
 */

package Hw2
import chisel3._
import chisel3.util._


object OpCode {
  val addi  = "b0010011".U
  val add   = "b0110011".U
  val sd    = "b0100011".U
  val ld    = "b0000011".U
  val beq   = "b1100011".U
}

object AluOp {
  val ld = "b00".U
  val sd = "b00".U
  val beq = "b01".U
  val reg = "b10".U
}

object AluCtrl {
  val and = "b0000".U(4.W)
  val or  = "b0001".U(4.W)
  val add = "b0010".U(4.W)
  val sub = "b0110".U(4.W)
}

/*
Task 1: ALU
*/

class ALU extends Module {
  val io = IO(new Bundle {
    val ctrl = Input(UInt(4.W))
    val a = Input(UInt(64.W))
    val b = Input(UInt(64.W))
    val res = Output(UInt(64.W))
    val zero = Output(Bool())
  })
  
  /* Your code starts here*/
  io.res := 0.U
  io.zero := true.B

  when(io.a >= io.b && io.a <= io.b){
    io.zero := true.B
  }.otherwise{
    io.zero := false.B
  }

  switch(io.ctrl){
    is(AluCtrl.and) {io.res := io.a & io.b}
    is(AluCtrl.or) {io.res := io.a | io.b}
    is(AluCtrl.add) {io.res := io.a + io.b}
    is(AluCtrl.sub) {io.res := io.a - io.b}
  }
  
  /*Your code ends here */
}

/*
Task 2: ImmGen
*/

class ImmGen extends Module {
  val io = IO(new Bundle {
    val insn = Input(UInt(32.W))
    val imm = Output(UInt(64.W))
  })

  /* Your code starts here*/

  io.imm := 0.U
  //io.imm := Cat(io.insn(3,1),io.insn(22,10))

  val opco = io.insn(6,0)
  val inst = io.insn

  switch(opco){
    is(OpCode.addi) { io.imm := Cat( Fill(53, inst(31)), inst(31,20)) }
    is(OpCode.add)  { io.imm := Cat( Fill(53, inst(31)), inst(31,20)) }
    is(OpCode.sd)   { io.imm := Cat( Fill(53, inst(31)), inst(31,25), inst(11,7)) }
    is(OpCode.ld)   { io.imm := Cat( Fill(53, inst(31)), inst(31,20)) }
    is(OpCode.beq)  { io.imm := Cat( Fill(53, inst(31)), inst(31), inst(7), inst(30,25), inst(11,8)) }
    
  }
  

  /*Your code ends here */

}

/*
Task 3: ALUControl
*/

class ALUControl extends Module {
  val io = IO(new Bundle {
    val aluOp = Input(UInt(2.W))
    val funct3 = Input(UInt(3.W))
    val funct7 = Input(UInt(7.W))
    val aluCtrl = Output(UInt(4.W))
  })

  /* Your code starts here*/
  io.aluCtrl := 0.U

  when(io.aluOp === 0.U ){
    io.aluCtrl := 2.U
  }.elsewhen(io.aluOp === 1.U){
    io.aluCtrl := 6.U
  }.elsewhen(io.aluOp === 2.U){
    io.aluCtrl := 2.U
  }


  /*Your code ends here */

}

/*
Task 4: Control
*/

class Control extends Module {
  val io = IO(new Bundle{
    val in = Input(UInt(7.W))
    val write_reg = Output(Bool())
    val aluSrcFromReg = Output(Bool())
    val memWrite = Output(Bool())
    val memToReg = Output(Bool())
    val aluOp = Output(UInt(2.W))
    val branch = Output(Bool())
  })
/* Your code starts here*/
  io.aluOp := 0.U
  io.write_reg := false.B
  io.aluSrcFromReg := false.B
  io.memWrite := false.B
  io.memToReg := false.B
  io.branch := false.B


switch(io.in){
  is(OpCode.addi){
    io.write_reg := true.B 
    io.aluSrcFromReg := false.B //
    io.memWrite := false.B 
    io.memToReg := false.B 
    io.aluOp := "b10".U
    io.branch := false.B 
  }

  is(OpCode.add){
    io.write_reg := true.B 
    io.aluSrcFromReg := true.B
    io.memWrite := false.B 
    io.memToReg := false.B 
    io.aluOp := "b10".U
    io.branch := false.B 
  }

  is(OpCode.sd){
    io.write_reg := false.B 
    io.aluSrcFromReg := false.B
    io.memWrite := true.B 
    io.memToReg := false.B 
    io.aluOp := "b00".U 
    io.branch := false.B 
  }

  is(OpCode.ld){
    io.write_reg := true.B 
    io.aluSrcFromReg := false.B
    io.memWrite := false.B 
    io.memToReg := true.B 
    io.aluOp := "b00".U 
    io.branch := false.B 
  }

  is(OpCode.beq){
    io.write_reg := false.B 
    io.aluSrcFromReg := false.B
    io.memWrite := false.B 
    io.memToReg := false.B 
    io.aluOp := "b01".U 
    io.branch := true.B 
  }
}
  /*Your code ends here */
 
}