package riscv
import chisel3._
import chisel3.util._

class StructuralDetector extends Module {
  val io = IO(new Bundle {
    val rs1_sel = Input(UInt(5.W))
    val rs2_sel = Input(UInt(5.W))
    val MEM_WB_regwrite = Input(UInt(1.W))
    val MEM_WB_rd = Input(UInt(5.W))
    val fwd_rs1 = Output(UInt(1.W))
    val fwd_rs2 = Output(UInt(1.W))
  })

  when(io.MEM_WB_regwrite === 1.U &&  io.MEM_WB_rd === io.rs1_sel) {
    io.fwd_rs1 := 1.U
  } .otherwise {
    io.fwd_rs1 := 0.U
  }   
  when(io.MEM_WB_regwrite === 1.U && io.MEM_WB_rd === io.rs2_sel) {
    io.fwd_rs2 := 1.U
  } .otherwise {
    io.fwd_rs2 := 0.U
  }

}