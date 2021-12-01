package UniRV64I

import chisel3._
import chisel3.util._
import chisel3.iotesters.PeekPokeTester
import firrtl_interpreter._
import scala.collection.mutable.ListBuffer
import scala.sys.process._
import scala.util.control._

object Hw3Tests {
  // (name, sourceName, result)
  // Test appear later assumes the test appear ealier succeeds.
  val testHome = "sw/test"
  val instructionTests = List(
    ("addinh","addinh.s",
"""
cycle |   pc | fetched_insn |  wb_insn | write |    wdata |     addr | read |    rdata | halted
    0 |    0 |      1008093 |        0 |     0 |        0 |        0 |    0 |        0 |      0
    1 |    4 |           13 |        0 |     0 |        0 |        0 |    0 |        0 |      0
    2 |    8 |           13 |        0 |     0 |        0 |        0 |    0 |        0 |      0
    3 |    c |           13 |        0 |     0 |        0 |        0 |    0 |        0 |      0
    4 |   10 |       103023 |  1008093 |     0 |        0 |        0 |    0 |        0 |      0
    5 |   14 |         5073 |       13 |     0 |        0 |        0 |    0 |        0 |      0
    6 |   18 |           53 |       13 |     0 |        0 |        0 |    0 |        0 |      0
    7 |   1c |           53 |       13 |     1 |       10 |        0 |    0 |        0 |      0
    8 |   20 |           53 |   103023 |     0 |        0 |        0 |    0 |        0 |      0
    9 |   24 |           53 |     5073 |     0 |        0 |        0 |    0 |        0 |      1
"""),
  ("addnh", "addnh.s",
"""
cycle |   pc | fetched_insn |  wb_insn | write |    wdata |     addr | read |    rdata | halted
    0 |    0 |      1000093 |        0 |     0 |        0 |        0 |    0 |        0 |      0
    1 |    4 |      2000113 |        0 |     0 |        0 |        0 |    0 |        0 |      0
    2 |    8 |           13 |        0 |     0 |        0 |        0 |    0 |        0 |      0
    3 |    c |           13 |        0 |     0 |        0 |        0 |    0 |        0 |      0
    4 |   10 |           13 |  1000093 |     0 |        0 |        0 |    0 |        0 |      0
    5 |   14 |       2081b3 |  2000113 |     0 |        0 |        0 |    0 |        0 |      0
    6 |   18 |           13 |       13 |     0 |        0 |        0 |    0 |        0 |      0
    7 |   1c |           13 |       13 |     0 |        0 |        0 |    0 |        0 |      0
    8 |   20 |           13 |       13 |     0 |        0 |        0 |    0 |        0 |      0
    9 |   24 |        1b083 |   2081b3 |     0 |        0 |        0 |    0 |        0 |      0
   10 |   28 |         5073 |       13 |     0 |        0 |        0 |    0 |        0 |      0
   11 |   2c |           53 |       13 |     0 |        0 |        0 |    0 |        0 |      0
   12 |   30 |           53 |       13 |     0 |        0 |       30 |    1 |        0 |      0
   13 |   34 |           53 |    1b083 |     0 |        0 |        0 |    0 |        0 |      0
   14 |   38 |           53 |     5073 |     0 |        0 |        0 |    0 |        0 |      1
"""),
  ("ldsdnh", "ldsdnh.s",
"""
cycle |   pc | fetched_insn |  wb_insn | write |    wdata |     addr | read |    rdata | halted
    0 |    0 |     20400093 |        0 |     0 |        0 |        0 |    0 |        0 |      0
    1 |    4 |           13 |        0 |     0 |        0 |        0 |    0 |        0 |      0
    2 |    8 |           13 |        0 |     0 |        0 |        0 |    0 |        0 |      0
    3 |    c |           13 |        0 |     0 |        0 |        0 |    0 |        0 |      0
    4 |   10 |       103423 | 20400093 |     0 |        0 |        0 |    0 |        0 |      0
    5 |   14 |           13 |       13 |     0 |        0 |        0 |    0 |        0 |      0
    6 |   18 |       803103 |       13 |     0 |        0 |        0 |    0 |        0 |      0
    7 |   1c |       80b183 |       13 |     1 |      204 |        8 |    0 |        0 |      0
    8 |   20 |           13 |   103423 |     0 |        0 |        0 |    0 |        0 |      0
    9 |   24 |           13 |       13 |     0 |        0 |        8 |    1 |        0 |      0
   10 |   28 |       113023 |   803103 |     0 |        0 |      20c |    1 |      204 |      0
   11 |   2c |         5073 |   80b183 |     0 |        0 |        0 |    0 |      20c |      0
   12 |   30 |           53 |       13 |     0 |        0 |        0 |    0 |        0 |      0
   13 |   34 |           53 |       13 |     1 |      204 |      204 |    0 |        0 |      0
   14 |   38 |           53 |   113023 |     0 |        0 |        0 |    0 |        0 |      0
   15 |   3c |           53 |     5073 |     0 |        0 |        0 |    0 |        0 |      1
"""),
  ("beqnh", "beqnh.s",
"""
cycle |   pc | fetched_insn |  wb_insn | write |    wdata |     addr | read |    rdata | halted
    0 |    0 |     20400093 |        0 |     0 |        0 |        0 |    0 |        0 |      0
    1 |    4 |     20400113 |        0 |     0 |        0 |        0 |    0 |        0 |      0
    2 |    8 |           13 |        0 |     0 |        0 |        0 |    0 |        0 |      0
    3 |    c |           13 |        0 |     0 |        0 |        0 |    0 |        0 |      0
    4 |   10 |           13 | 20400093 |     0 |        0 |        0 |    0 |        0 |      0
    5 |   14 |         b083 | 20400113 |     0 |        0 |        0 |    0 |        0 |      0
    6 |   18 |        13103 |       13 |     0 |        0 |        0 |    0 |        0 |      0
    7 |   1c |           13 |       13 |     0 |        0 |        0 |    0 |        0 |      0
    8 |   20 |           13 |       13 |     0 |        0 |      204 |    1 |        0 |      0
    9 |   24 |           13 |     b083 |     0 |        0 |      204 |    1 |      204 |      0
   10 |   28 |           13 |    13103 |     0 |        0 |        0 |    0 |      204 |      0
   11 |   2c |      2208263 |       13 |     0 |        0 |        0 |    0 |        0 |      0
   12 |   30 |           13 |       13 |     0 |        0 |        0 |    0 |        0 |      0
   13 |   34 |           13 |       13 |     0 |        0 |        0 |    0 |        0 |      0
   14 |   34 |           13 |       13 |     0 |        0 |        0 |    0 |        0 |      0
   15 |   50 |           13 |  2208263 |     0 |        0 |        0 |    0 |        0 |      0
   16 |   54 |           13 |        0 |     0 |        0 |        0 |    0 |        0 |      0
   17 |   58 |       103023 |        0 |     0 |        0 |        0 |    0 |        0 |      0
   18 |   5c |         8c63 |       13 |     0 |        0 |        0 |    0 |        0 |      0
   19 |   60 |           13 |       13 |     0 |        0 |        0 |    0 |        0 |      0
   20 |   64 |           13 |       13 |     1 |      204 |        0 |    0 |        0 |      0
   21 |   64 |           13 |   103023 |     0 |        0 |        0 |    0 |        0 |      0
   22 |   68 |           13 |     8c63 |     0 |        0 |        0 |    0 |        0 |      0
   23 |   6c |           13 |        0 |     0 |        0 |        0 |    0 |        0 |      0
   24 |   70 |         5073 |        0 |     0 |        0 |        0 |    0 |        0 |      0
   25 |   74 |           13 |       13 |     0 |        0 |        0 |    0 |        0 |      0
   26 |   78 |           13 |       13 |     0 |        0 |        0 |    0 |        0 |      0
   27 |   7c |           13 |       13 |     0 |        0 |        0 |    0 |        0 |      0
   28 |   80 |           13 |     5073 |     0 |        0 |        0 |    0 |        0 |      1
"""))
}