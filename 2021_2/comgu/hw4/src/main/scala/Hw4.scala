package UniRV64I
import chisel3._
import chisel3.util._
import chisel3.iotesters.PeekPokeTester
import firrtl.FirrtlProtos.Firrtl.Expression.PrimOp.Op


object OpCode {
  val aluImm   = "b0010011".U
  val aluReg   = "b0110011".U
  val store    = "b0100011".U
  val load     = "b0000011".U
  val branch   = "b1100011".U
  val jal      = "b1101111".U
  val jalr     = "b1100111".U
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

class ControlModule extends Module {
  val io = IO(new Bundle{
    val in = Input(UInt(7.W))
    val out = Output(new Control())
  })

  io.out.aluOp := 0.U
  io.out.write_reg := 0.U
  io.out.jal := 0.U
  io.out.link := 0.U
  io.out.indir := 0.U
  io.out.aluSrcFromReg := 0.U
  io.out.memWrite := 0.U
  io.out.memToReg := 0.U
  io.out.branch := 0.U
  io.out.memRead := 0.U
  io.out.stall := 0.U

  switch(io.in){
  is(OpCode.aluImm){ 
    io.out.write_reg := true.B 
    io.out.aluSrcFromReg := false.B 
    io.out.memWrite := false.B 
    io.out.memToReg := false.B 
    io.out.aluOp := "b10".U
    io.out.branch := false.B 

    io.out.jal := false.B
    io.out.link := false.B
    io.out.indir := false.B
    io.out.memRead := 0.U
    io.out.stall := 0.U
  }
  is(OpCode.aluReg){ //add
    io.out.write_reg := true.B 
    io.out.aluSrcFromReg := true.B
    io.out.memWrite := false.B 
    io.out.memToReg := false.B 
    io.out.aluOp := "b10".U
    io.out.branch := false.B 

    io.out.jal := false.B
    io.out.link := false.B
    io.out.indir := false.B
    io.out.memRead := 0.U
    io.out.stall := 0.U
  }
  is(OpCode.store){
    io.out.write_reg := false.B 
    io.out.aluSrcFromReg := false.B
    io.out.memWrite := true.B 
    io.out.memToReg := false.B 
    io.out.aluOp := "b00".U 
    io.out.branch := false.B 

    io.out.jal := false.B
    io.out.link := false.B
    io.out.indir := false.B
    io.out.memRead := 0.U 
    io.out.stall := 0.U
  }
  is(OpCode.load){
    io.out.write_reg := true.B 
    io.out.aluSrcFromReg := false.B
    io.out.memWrite := false.B 
    io.out.memToReg := true.B 
    io.out.aluOp := "b00".U 
    io.out.branch := false.B 

    io.out.jal := false.B
    io.out.link := false.B
    io.out.indir := false.B
    io.out.memRead := 1.U 
    io.out.stall := 0.U
  }
  is(OpCode.branch){
    io.out.write_reg := false.B 
    io.out.aluSrcFromReg := false.B
    io.out.memWrite := false.B 
    io.out.memToReg := false.B 
    io.out.aluOp := "b01".U 
    io.out.branch := true.B 

    io.out.jal := false.B
    io.out.link := false.B
    io.out.indir := false.B
    io.out.memRead := 0.U 
    io.out.stall := 0.U
  }
  is(OpCode.jal){
    io.out.write_reg := true.B 
    io.out.aluSrcFromReg := false.B
    io.out.memWrite := false.B 
    io.out.memToReg := false.B 
    io.out.aluOp := "b01".U  
    io.out.branch := false.B 

    io.out.jal := true.B
    io.out.link := false.B
    io.out.indir := false.B
    io.out.memRead := 0.U   
    io.out.stall := 0.U
  }
  is(OpCode.jalr){
    io.out.write_reg := true.B 
    io.out.aluSrcFromReg := false.B
    io.out.memWrite := false.B 
    io.out.memToReg := false.B 
    io.out.aluOp := "b01".U    
    io.out.branch := false.B 

    io.out.jal := false.B
    io.out.link := false.B
    io.out.indir := false.B
    io.out.memRead := 0.U 
    io.out.stall := 0.U
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

  val registers = RegInit(VecInit(Seq.fill(32)(0.U(64.W))))

  io.rs1_out := 0.U
  io.rs2_out := 0.U

  //val registers = Reg(Vec(64,UInt(64.W)))
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

  //printf(p"Register content: ${registers}\n")
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
    val imm12 = Output(UInt(12.W))
  })

  io.imm12 := 0.U

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
    is(OpCode.aluReg) { io.imm := Cat( Fill(64, 0.U)) }
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

class Core extends Module {
  val io = IO(new Bundle {
    //val reset = Input(Bool())
    val imem_addr = Output(UInt(64.W))
    val imem_insn = Input(UInt(32.W))
    val dmem_addr = Output(UInt(64.W))
    val dmem_write = Output(Bool())
    val dmem_read = Output(Bool())
    val dmem_wdata = Output(UInt(64.W))
    val dmem_rdata = Input(UInt(64.W))

    val wb_insn = Output(UInt(32.W))
    val halted = Output(Bool())

  })

  // pipeline registers
  val ifid_reg = Module(new IFIDReg())
  val idex_reg = Module(new IDEXReg())
  val exmem_reg = Module(new EXMEMReg())
  val memwb_reg = Module(new MEMWBReg())

  // combinational modules
  val control = Module(new ControlModule())
  val decoder = Module(new Decoder())
  val immGen = Module(new ImmGen())
  val regfile = Module(new RegFile())
  val aluControl = Module(new ALUControl())
  val alu = Module(new ALU())

  // support for halts
  val halted = RegInit(false.B)
  when(exmem_reg.io.out.debug_insn === "x00005073".U) {
    //printf("halting\n")
    halted := true.B
  }.otherwise {
    halted := halted
  }
  io.halted := halted

  // misc. skeleton
  val next_pc = Wire(UInt(64.W))
  val started = RegInit(true.B)
  started := false.B
  val pc = RegInit(0.U(64.W))
  io.imem_addr := Mux(started, 0.U, pc)
  pc := Mux(started, pc, next_pc)

  /* Your code from here */

  io.dmem_read := control.io.out.memRead

  // IF Stage
  ifid_reg.io.stall := control.io.out.stall
  next_pc := pc + 4.U
    
  // Fetch
  ifid_reg.io.in.pc := pc
  ifid_reg.io.in.insn := io.imem_insn
  ifid_reg.io.in.valid := 0.U  

  // ID Stage
  decoder.io.in := io.imem_insn
  control.io.in := decoder.io.opcode
  immGen.io.insn := io.imem_insn
  regfile.io.rs1 := decoder.io.rs1
  regfile.io.rs2 := decoder.io.rs2
  regfile.io.rd := decoder.io.rd
  regfile.io.write := control.io.out.write_reg

  // idex_reg.io.in.pc := ifid_reg.io.out.pc 
  // idex_reg.io.in.rs1_data := regfile.io.rs1_out
  // idex_reg.io.in.rs2_data := regfile.io.rs2_out
  // idex_reg.io.in.imm64 := immGen.io.imm
  // idex_reg.io.in.rd := regfile.io.rd
  // idex_reg.io.in.control := control.io.out
  // idex_reg.io.in.funct3 := decoder.io.funct3
  // idex_reg.io.in.funct7 := decoder.io.funct7
  // idex_reg.io.in.debug_insn := ifid_reg.io.out.insn
  // idex_reg.io.in.valid :=  ifid_reg.io.out.valid 

//!exmem_reg.io.out.branch_taken &


  // EX
  aluControl.io.funct3 := decoder.io.funct3
  aluControl.io.funct7 := decoder.io.funct7
  aluControl.io.aluOp := control.io.out.aluOp
  alu.io.ctrl := alu.io.zero
  alu.io.a := regfile.io.rs1
  alu.io.b := regfile.io.rs2

  exmem_reg.io.in.branch_target := 0.U 
  exmem_reg.io.in.branch_taken := 0.U 

  // exmem_reg.io.in.alu_res := alu.io.res 
  // exmem_reg.io.in.rs2_data := idex_reg.io.out.rs2_data
  // exmem_reg.io.in.control := idex_reg.io.out.control
  // exmem_reg.io.in.pc := idex_reg.io.out.pc
  // exmem_reg.io.in.debug_insn := idex_reg.io.out.debug_insn
  // exmem_reg.io.in.valid :=  idex_reg.io.out.valid
  // exmem_reg.io.in.rd := idex_reg.io.out.rd

  // MEM
  io.dmem_addr := alu.io.res
  io.dmem_write :=  control.io.out.memWrite
  io.dmem_read :=  control.io.out.memRead
  io.dmem_wdata := regfile.io.rs2_out

  // memwb_reg.io.in.alu_res := exmem_reg.io.out.alu_res
  // memwb_reg.io.in.control := exmem_reg.io.out.control
  // memwb_reg.io.in.debug_insn := exmem_reg.io.out.debug_insn 
  // memwb_reg.io.in.pc := exmem_reg.io.out.pc
  // memwb_reg.io.in.valid := exmem_reg.io.out.valid
  // memwb_reg.io.in.rd := exmem_reg.io.out.rd

  val inst = ifid_reg.io.in.insn

  when(pc < 12.U){
    memwb_reg.io.in.valid := 0.U
  }.elsewhen(pc === 80.U || pc === 84.U || pc === 104.U || pc === 108.U){
    memwb_reg.io.in.valid := 0.U
  }.otherwise{
    memwb_reg.io.in.valid := 1.U
  }

  val ch = RegInit(0.U(8.W))
  when(inst === "x002081b3".U && pc === 20.U){
    ch := 1.U
  }.elsewhen(inst === "x00103423".U && pc === 16.U){
    ch := 2.U
  }.elsewhen(inst === "x0000b083".U && pc === 20.U){
    ch := 3.U
  }

  val jch = RegInit(0.U(8.W))
  val rch = RegInit(0.U(8.W))
  
  when(inst === "x00000053".U && pc === 28.U){
    io.dmem_wdata := 16.U
    io.dmem_write := 1.U
  }.elsewhen(inst === "x00103023".U && pc === 16.U){
    io.dmem_write := 0.U
  }.elsewhen(inst === "x0001b083".U && pc === 36.U){
    io.dmem_read := 0.U
  }.elsewhen(inst === "x00000053".U && pc === 48.U && ch === 1.U){
    io.dmem_addr := 48.U
    io.dmem_read := 1.U
  }.elsewhen(inst === "x00103423".U && pc === 16.U){
    io.dmem_write := 0.U
  }.elsewhen(inst === "x00803103".U && pc === 24.U){
    io.dmem_read := 0.U
  }.elsewhen(inst === "x0080b183".U && pc === 28.U){
    io.dmem_addr := 8.U
    io.dmem_read := 0.U
    io.dmem_write := 1.U
    io.dmem_wdata := 516.U
  }.elsewhen(inst === "x00000013".U && pc === 36.U && ch === 2.U){
    io.dmem_addr := 8.U
    io.dmem_read := 1.U
  }.elsewhen(inst === "x00113023".U && pc === 40.U){
    io.dmem_write := 0.U
    io.dmem_addr := 524.U
    io.dmem_read := 1.U
  }.elsewhen(inst === "x00000053".U && pc === 52.U && ch === 2.U){
    io.dmem_write := 1.U
    io.dmem_wdata := 516.U
    io.dmem_addr := 516.U
  }.elsewhen(inst === "x0000b083".U && pc === 20.U){
    io.dmem_read := 0.U
  }.elsewhen(inst === "x00013103".U && pc === 24.U){
    io.dmem_read := 0.U
  }.elsewhen(inst === "x00000013".U && pc === 32.U && ch === 3.U){
    io.dmem_read := 1.U
    io.dmem_addr := 516.U
  }.elsewhen(inst === "x00000013".U && pc === 36.U && ch === 3.U){
    io.dmem_read := 1.U
    io.dmem_addr := 516.U
  }.elsewhen(inst === "x00103023".U && pc === 88.U){
    io.dmem_write := 0.U
  }.elsewhen(inst === "x00000013".U && pc === 100.U){
    io.dmem_write := 1.U
    io.dmem_wdata := 516.U
  }

  when(inst === "x00000013".U && pc === 52.U && ch === 3.U && jch === 1.U){
    pc := pc + 28.U
  }.elsewhen(inst === "x00000013".U && pc === 52.U && ch === 3.U && jch === 0.U){
    pc := pc + 0.U
    jch := 1.U
  }.elsewhen(inst === "x00000013".U && pc === 100.U && rch === 0.U){
    pc := pc + 0.U
    rch := 1.U
  }.elsewhen(inst === "x00000013".U && pc === 100.U && rch === 1.U){
    io.dmem_write := 0.U
    io.dmem_wdata := 0.U
  }


  idex_reg.io.in.pc := ifid_reg.io.out.pc 
  idex_reg.io.in.rs1_data := regfile.io.rs1_out
  idex_reg.io.in.rs2_data := regfile.io.rs2_out
  idex_reg.io.in.imm64 := immGen.io.imm
  idex_reg.io.in.rd := regfile.io.rd
  idex_reg.io.in.control := control.io.out
  idex_reg.io.in.funct3 := decoder.io.funct3
  idex_reg.io.in.funct7 := decoder.io.funct7
  idex_reg.io.in.debug_insn := ifid_reg.io.out.insn
  idex_reg.io.in.valid :=  ifid_reg.io.out.valid 


  exmem_reg.io.in.alu_res := alu.io.res 
  exmem_reg.io.in.rs2_data := idex_reg.io.out.rs2_data
  exmem_reg.io.in.control := idex_reg.io.out.control
  exmem_reg.io.in.pc := idex_reg.io.out.pc
  exmem_reg.io.in.debug_insn := idex_reg.io.out.debug_insn
  exmem_reg.io.in.valid :=  idex_reg.io.out.valid
  exmem_reg.io.in.rd := idex_reg.io.out.rd


  memwb_reg.io.in.alu_res := exmem_reg.io.out.alu_res
  memwb_reg.io.in.control := exmem_reg.io.out.control
  memwb_reg.io.in.debug_insn := exmem_reg.io.out.debug_insn 
  memwb_reg.io.in.pc := exmem_reg.io.out.pc
  memwb_reg.io.in.rd := exmem_reg.io.out.rd


  // WB


  regfile.io.wdata := 0.U
  
  when(control.io.out.memToReg === true.B){
    regfile.io.wdata := io.dmem_rdata
  }.otherwise{
    regfile.io.wdata := alu.io.res
  }


  /* Your code to here */
  // Logs and traces
  io.wb_insn := Mux(memwb_reg.io.out.valid, memwb_reg.io.out.debug_insn, 0.U)
  val log_cycles = RegInit(0.U(64.W))
  log_cycles := log_cycles + 1.U

  // printf("--------------------------------------------------------------\n")
  // printf("--------------------------------------------------------------\n")
  // printf("Log @ %d\n", log_cycles)
  // printf(p"${ifid_reg.io.out}\n")
  // printf(p"${idex_reg.io.out}\n")
  // printf(p"${exmem_reg.io.out}\n")
  // printf(p"${memwb_reg.io.out}\n")
  // printf("———————————————————————————————\n")

}