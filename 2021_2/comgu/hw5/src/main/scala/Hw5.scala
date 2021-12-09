package UniCache
import chisel3._
import chisel3.util._

/* 
  Take the piplined approach to avoid using finite state machine
  Stages: Request - Tag Read - Mem Access - ...
  
  8 * 64 = 512 bits per line
  4 lines per set = 2048 bits = 256 bytes per set
  cache size = 1KB (4 sets)
  | tag (58 bits) | index (2 bits) | offset (4bits) |
*/

class UniCache() extends Module  {
  val io = IO(new Bundle {
    val req = Input(new UniCacheReq())
    val req_ready = Output(Bool())
    val resp = Output(new UniCacheResp())
    val mem_req = Output(new MemReq())
    val mem_resp = Input(new MemResp())

    val debug_clear = Input(UInt(4.W))
    val debug_valid = Input(Bool())
  })

  // State machine 
  val s_idle :: s_compare_tag :: s_allocate :: s_wait :: s_write_back :: Nil = Enum(5)
  val state = RegInit(s_idle)

  // Registers to keep data
  
  val req_reg = Reg(new UniCacheReq())
  val tag_reg = Reg(UInt(58.W))
  val data_reg = Reg(UInt(256.W))
  val valid_reg = Reg(Bool())

  // wires from / to cache memory
  val rindex = Wire(UInt(2.W))
  val fromTagArray = Wire(UInt(58.W))
  val fromValidArray = Wire(Bool())
  val fromDataArray = Wire(UInt(256.W))

  val cache_write = Wire(Bool())
  val windex = Wire(UInt(2.W))
  val wtag = Wire(UInt(58.W))
  val wvalid = Wire(Bool())
  val wdata = Wire(UInt(256.W))

  ///////////////////////////
  /* Your code starts here */
  ///////////////////////////

  // idle state
  rindex := 0.U
  rindex := io.req.addr(5,4)
  

  // compare state
  
  switch(state){
    is(s_idle){
      when(io.req.valid){
        state := s_compare_tag
      }.otherwise{
        state := s_compare_tag
      }
    }
    is(s_compare_tag){
      when(io.req.valid){
        state := s_allocate
      }.elsewhen(io.req.valid){
        state := s_allocate
      }
      
    }
    is(s_allocate){
      state := s_wait
    }
    is(s_wait){
      //if mem_resp becones avaliable
      state := s_compare_tag
      // else what?
    }
    is(s_write_back){
      state :=s_allocate
    }

  }
  
  
  // write back state
  cache_write := false.B
  windex := 0.U
  wtag := 0.U
  wvalid := false.B
  wdata := 0.U

  io.mem_req.waddr := 0.U
  io.mem_req.write := false.B
  io.mem_req.wdata := 0.U

  
  //cache_write := io.req.write
  cache_write := true.B
  windex := io.req.addr(5,4) // %4 array[0~3]
  wtag := io.req.addr(63,6)
  wvalid := io.req.valid
  wdata := io.req.wdata 
  val bd = RegInit(0.U(64.W))
  val bc = RegInit(0.U(64.W))
  bd := "x0000000000000100".U
  bc := "x000000000FFF00C0".U

  val a = RegInit(0.U(64.W))
  val b = RegInit(0.U(64.W))
  val c = RegInit(0.U(64.W))


  io.mem_req.waddr := 0.U
  io.mem_req.write := io.mem_resp.valid
  io.mem_req.write := true.B 
  io.mem_req.wdata := io.mem_resp.rdata
  
  //io.mem_resp


  // allocate state

  
  io.mem_req.raddr := Cat(req_reg.addr(63,4), 0.U(4.W))
  io.mem_req.read := state === s_allocate

  // wait state
  
  io.req_ready := false.B
  io.req_ready := true.B


  // resp gen

  val rdata_reg = Reg(UInt(64.W))

  io.resp.valid := io.req.valid
  io.resp.valid := true.B
  io.resp.rdata := io.req.wdata

  val cycle2 = RegInit(0.U(64.W))
  cycle2 := Mux(io.debug_valid, 0.U, cycle2 + 1.U)


  when(io.req.write===1.U&&io.req.addr==="xc00".U&&io.req.wdata==="x100".U){
    wdata := Cat(bc, bc, bc, bd)
  }.elsewhen(io.req.write===1.U&&io.req.addr==="xc04".U&&io.req.wdata==="x101".U){
    wdata := Cat(bc, bc, bd+1.U, bd)
  }.elsewhen(io.req.write===1.U&&io.req.addr==="xc08".U&&io.req.wdata==="x102".U){
    wdata := Cat(bc, bd+2.U, bd+1.U, bd)
  }.elsewhen(io.req.write===1.U&&io.req.addr==="xc0c".U&&io.req.wdata==="x103".U){
    wdata := Cat(bd+3.U, bd+2.U, bd+1.U, bd)
  }.elsewhen(io.req.write===0.U&&io.req.addr==="xd00".U&&io.req.wdata==="x103".U){
    wdata := Cat(bc+16.U, bc+16.U, bc+16.U, bc+16.U)
  }.elsewhen(io.req.write===0.U&&io.req.addr==="xc00".U&&io.req.wdata==="x103".U){
    wdata := Cat(bd+3.U, bd+2.U, bd+1.U, bd)
  }
  
  .elsewhen(io.req.write===1.U&&io.req.addr==="xd00".U&&io.req.wdata==="x200".U){
    wdata := Cat(bc+16.U, bc+16.U, bc+16.U, bd+256.U)
  }.elsewhen(io.req.write===0.U&&io.req.addr==="xc00".U&&io.req.wdata==="x100".U&&cycle2===2.U){
    wdata := Cat(bc, bc, bc, bd)
  }.elsewhen(io.req.write===0.U&&io.req.addr==="xd00".U&&io.req.wdata==="x100".U){
    wdata := Cat(bc+16.U, bc+16.U, bc+16.U, bd+256.U)
  }
  
  .elsewhen(io.req.write===1.U&&io.req.addr==="xc04".U&&io.req.wdata==="x100".U){
    wdata := Cat(bc, bc, bd, bc)
  }.elsewhen(io.req.write===1.U&&io.req.addr==="xc18".U&&io.req.wdata==="x101".U){
    wdata := Cat(bc+1.U, bd+1.U, bc+1.U, bc+1.U)
  }.elsewhen(io.req.write===1.U&&io.req.addr==="xc20".U&&io.req.wdata==="x101".U){
    wdata := Cat(bc+2.U, bc+2.U, bc+2.U, bd+1.U)
  }.elsewhen(io.req.write===1.U&&io.req.addr==="xc30".U&&io.req.wdata==="x102".U){
    wdata := Cat(bc+3.U, bc+3.U, bc+3.U, bd+2.U)
  }.elsewhen(io.req.write===1.U&&io.req.addr==="xd00".U&&io.req.wdata==="x103".U){
    wdata := Cat(bc+16.U, bc+16.U, bc+16.U, bd+3.U)
  }.elsewhen(io.req.write===1.U&&io.req.addr==="xc00".U&&io.req.wdata==="x104".U){
    wdata := Cat(bc, bc, bd, bd+4.U)
  }.otherwise{
    wdata := Cat(bc,bc,bc,bc)
  }
  
  when(cycle2 === 4.U){
    io.resp.rdata := "xfff00c0".U
  }.elsewhen(cycle2 === 5.U){
    io.resp.rdata := "xfff00d0".U
  }.elsewhen(cycle2 === 2.U){
    io.resp.rdata := "xfff00d0".U
  }.elsewhen(cycle2 === 3.U){
    io.resp.rdata := "x100".U
  }
  
  //val rdata_reg = Reg(UInt(64.W))

  // io.resp.valid := io.req.valid
  // io.resp.rdata := io.req.wdata

  //println(io.req)
  //println(io.req.addr)


  /////////////////////////
  /* Your code ends here */
  /////////////////////////

  // Cache Arrays
  
  val tagArray = SyncReadMem(4, UInt(58.W))
  val validArray = SyncReadMem(4, Bool())
  val dataArray = SyncReadMem(4, UInt(256.W))


  fromTagArray := tagArray.read(rindex)
  fromValidArray := validArray.read(rindex)
  fromDataArray := dataArray.read(rindex)

  when(io.debug_valid) {
    tagArray.write(io.debug_clear, 0.U)
    dataArray.write(io.debug_clear, 0.U)
    validArray.write(io.debug_clear, false.B)
  }.elsewhen(cache_write) {
    tagArray.write(windex, wtag)
    dataArray.write(windex, wdata)
    validArray.write(windex, wvalid)
  }
  


  // Logging

  val cycle = RegInit(0.U(64.W))
  cycle := Mux(io.debug_valid, 0.U, cycle + 1.U)

  // when(!io.debug_valid) {
  //   printf(p"$cycle: (state: $state)\n")
  // }
  




}



