package hw4

import scala.collection.immutable.HashMap 
import hw4._


package object hw4 {
  type Env = HashMap[Var,LocVal]
}


case class Mem(m: HashMap[LocVal,Val], top: Int) {
  def extended(v: Val): (Mem, LocVal) = {
    val new_mem = Mem(m.updated(LocVal(top),v), top+1)
    (new_mem,LocVal(top))
  }
  def updated(l: LocVal, new_val: Val): Option[Mem] = {
    m.get(l) match {
      case Some(v) => Some(Mem(m.updated(l, new_val), top))
      case None => None
    }
  }
  def get(l: LocVal): Option[Val] = m.get(l)
  def getLocs(): List[LocVal] = m.keySet.toList
}

sealed trait Val
case object SkipVal extends Val
case class IntVal(n: Int) extends Val
case class BoolVal(b: Boolean) extends Val
case class ProcVal(args: List[Var], expr: Expr, env: Env) extends Val
case class LocVal(l: Int) extends Val
sealed trait RecordValLike extends Val
case object EmptyRecordVal extends RecordValLike
case class RecordVal(field: Var, loc: LocVal, next: RecordValLike) extends RecordValLike


sealed trait Program
sealed trait Expr extends Program
case object Skip extends Expr
case object False extends Expr
case object True extends Expr
case class NotExpr(expr: Expr) extends Expr
case class Const(n: Int) extends Expr
case class Var(s: String) extends Expr {
  override def toString = s"Var(${"\""}${s}${"\""})"
}
case class Add(l: Expr, r: Expr) extends Expr
case class Sub(l: Expr, r: Expr) extends Expr
case class Mul(l: Expr, r: Expr) extends Expr
case class Div(l: Expr, r: Expr) extends Expr
case class LTEExpr(l: Expr, r: Expr) extends Expr
case class EQExpr(l: Expr, r: Expr) extends Expr
case class Iszero(c: Expr) extends Expr
case class Ite(c: Expr, t: Expr, f: Expr) extends Expr
case class Let(i: Var, v: Expr, body: Expr) extends Expr
case class Proc(args: List[Var], expr: Expr) extends Expr
case class Asn(v: Var, e: Expr) extends Expr
case class BeginEnd(expr: Expr) extends Expr
case class FieldAccess(record: Expr, field: Var) extends Expr
case class FieldAssign(record: Expr, field: Var, new_val: Expr) extends Expr
case class Block(f: Expr, s: Expr) extends Expr
case class PCallV(ftn: Expr, arg: List[Expr]) extends Expr
case class PCallR(ftn: Expr, arg: List[Var]) extends Expr
case class WhileExpr(cond: Expr, body: Expr) extends Expr

sealed trait RecordLike extends Expr
case object EmptyRecordExpr extends RecordLike
case class RecordExpr(field: Var, initVal: Expr, next: RecordLike) extends RecordLike





object MiniCInterpreter {

  case class Result(v: Val, m: Mem)
  case class UndefinedSemantics(msg: String = "", cause: Throwable = None.orNull) extends Exception("Undefined Semantics: " ++ msg, cause)
  sealed trait ResultList
  case object ResultNil extends ResultList
  case class ResultCons(h: Result, t: ResultList) extends ResultList
  sealed trait Env_MemList
  case object Env_MemNil extends Env_MemList
  case class Env_MemCons(h: (Env, Mem), t: Env_MemList) extends Env_MemList
  sealed trait EnvList
  case object EnvNil extends EnvList
  case class EnvCons(h: Env, t: EnvList) extends EnvList
  sealed trait MemList
  case object MemNil extends MemList
  case class MemCons(h: (Mem, LocVal), t: MemList) extends MemList

  def Search(r: RecordValLike, field: Var): RecordVal = r match {
    case EmptyRecordVal => throw UndefinedSemantics("No such record val")
    case r: RecordVal => if (r.field.s.equals(field.s)) r else Search(r.next, field)
  }

  def LoopEval(env: Env, mem: Mem, args: List[Expr]) : ResultList = args match{
    case List() => ResultNil
    case _ =>
      val earg = eval(env, mem, args.head)
      ResultCons(earg, LoopEval(env, earg.m, args.tail))
  }

  def FindMem(list: ResultList) : Mem = list match{
    case ResultCons(h, t) => t match{
      case ResultNil => h.m
      case _ => FindMem(t)
    }
  }

  def LoopAssign(args: List[Var], values: ResultList, e: Env, m: Mem) : Env_MemList = args match{
    case List() => Env_MemNil
    case _ =>
      val new_env = e + (args.head -> LocVal(m.top))
      values match{
        case ResultCons(h, t) => 
          val new_mem = m.extended(h.v)
          new_mem match{
            case (new_mem, LocVal(top)) => Env_MemCons((new_env, new_mem), LoopAssign(args.tail, t, new_env, new_mem))
          }
      }
  }

  def FindMemEnv(list: Env_MemList) : (Env, Mem) = list match{
    case Env_MemCons(h, t) => t match{
      case Env_MemNil => h
      case _ => FindMemEnv(t)
    }
  }

  def ExtendEnv(args: List[Var], index: Int, env: Env, loc: LocVal) = {
    env + (args(index) -> loc)
  }

  def ExtendMemAndEnv(args: List[Var], env: Env, mem: Mem, expr1: List[Expr], expr2: List[Expr], index: Int): (Env, Mem) = {
    expr1 match {
      case Nil => (env, mem)
      case ::(head, next) => {
        val new_index = index + 1
        val temp = eval(env, mem, head)
        val new_mem = mem.extended(temp.v)
        val new_env = ExtendEnv(args, index, env, new_mem._2)
        ExtendMemAndEnv(args, new_env, new_mem._1, next, expr2, new_index)
      }
    }
  }

  def ExtendEnv(procargs1: List[Var], procargs2: List[Var], arg: List[Var], env: Env, index: Int): Env = {
    procargs1 match {
      case Nil => env
      case ::(head, next) => {
        val new_index = index+1
        if (!env.contains(arg(index))) throw UndefinedSemantics("No such variable")
        val new_env = env + (head -> env(arg(index)))
        ExtendEnv(next, procargs2, arg, new_env, new_index)
      }
    }
  }

  
  def eval(env: Env, mem: Mem, expr: Expr): Result = expr match {
    case Skip => Result(SkipVal, mem)
    case True => Result(BoolVal(true), mem)
    case False => Result(BoolVal(false), mem)
    case Const(n) => Result(IntVal(n), mem)

    case NotExpr(expr) => eval(env, mem, expr).v match {
      case v: BoolVal => {
        val temp1 = mem.extended(v)
        Result(BoolVal(!v.b), temp1._1)
      }
      case _ => throw UndefinedSemantics("Not a bool type")
    }

    case Var(s) =>
      if (env.exists((a: (Var, Val)) => a._1 == Var(s))) {
        val temp = env(Var(s))
        temp match {
          case loc: LocVal => Result(mem.get(loc).get, mem)
          case _ => Result(temp, mem)
        }
      } 
      else {
        throw UndefinedSemantics("1")
      }

    case Add(l,r) => {
      val temp1 = eval(env,mem,l)
      val temp2 = eval(env,temp1.m,r)
      (temp1.v, temp2.v) match{
        case (IntVal(x), IntVal(y)) => Result(IntVal(x+y), temp2.m)
        case _ => throw UndefinedSemantics("Type error occur")
      }
    }

    case Sub(l,r) => {
      val temp1 = eval(env,mem,l)
      val temp2 = eval(env,temp1.m,r)
      (temp1.v, temp2.v) match{
        case (IntVal(x), IntVal(y)) => Result(IntVal(x-y), temp2.m)
        case _ => throw UndefinedSemantics("Type error occur")
      }
    }

    case Mul(l,r) => {
      val temp1 = eval(env,mem,l)
      val temp2 = eval(env,temp1.m,r)
      (temp1.v, temp2.v) match{
        case (IntVal(x), IntVal(y)) => Result(IntVal(x*y), temp2.m)
        case _ => throw UndefinedSemantics("Type error occur")
      }
    }
    
    case Div(l,r) => {
      val temp1 = eval(env,mem,l)
      val temp2 = eval(env,temp1.m,r)
      (temp1.v, temp2.v) match{
        case (IntVal(x), IntVal(y)) => {
          if(y==0) throw UndefinedSemantics("Can't divide 0")
          else Result(IntVal(x/y), temp2.m)
        }
        case _ => throw UndefinedSemantics("Type error occur")
      }
    }
    
    case LTEExpr(l,r) => {
      val temp1 = eval(env,mem,l)
      val temp2 = eval(env,temp1.m,r)
      (temp1.v, temp2.v) match{
        case (IntVal(x), IntVal(y)) => Result(BoolVal(x <= y), temp2.m)
        case _ => throw UndefinedSemantics("Type error occur")
      }
    }

    case EQExpr(l,r) => {
      val temp1 = eval(env,mem,l)
      val temp2 = eval(env,temp1.m,r)
      (temp1.v, temp2.v) match{
        case (IntVal(x), IntVal(y)) => Result(BoolVal(x == y), temp2.m)
        case (BoolVal(x), BoolVal(y)) => Result(BoolVal(x == y), temp2.m)
        case (SkipVal, SkipVal) => Result(BoolVal(true), mem)
        case (EmptyRecordVal, EmptyRecordVal) => Result(BoolVal(true), mem)
        case _ => throw UndefinedSemantics("Type error occur")
      }
    }

    case Iszero(c) => eval(env, mem, c).v match {
      case (x: IntVal) => {
        val temp1 = mem.extended(x)                 // extend instead of add
        Result(BoolVal(x.n == 0), temp1._1)
      }
      case _ => throw UndefinedSemantics("Type error occur")
    }

    case Ite(c, t, f) => eval(env, mem, c).v match {
      case v: BoolVal => {
        val temp1 = mem.extended(v)                 // extend instead of add and .__1 because mem is first elem
        if (v.b) eval(env, temp1._1, t)
        else eval(env, temp1._1, f)
      }
      case _ => throw UndefinedSemantics("Type error occur")
    }
  
    case Let(i, v, body) => {
      val temp = eval(env, mem, v)
      val new_mem = temp.m.extended(temp.v)
      val new_env = env + (i -> new_mem._2)
      eval(new_env, new_mem._1, body)
    }

    case Proc(args, expr) => {
      Result(ProcVal(args, expr, env), mem)
    }

    case Asn(v, e) => {
      val temp2 = eval(env, mem, e)
      v match {
        case Var(s) => {
          if (env.exists((a: (Var, Val)) => a._1 == Var(s))) {
            val res = env(Var(s))
            res match {
              case l: LocVal => {
                val temp3 = temp2.m.updated(l, temp2.v)
                Result(temp2.v, temp3.get)
              }
              case _ => throw UndefinedSemantics("Reassignment to Val")
            }
          } else {
            throw UndefinedSemantics("No such variable")
          }

        }
        case _ => throw UndefinedSemantics("Type error occur")
      }
    }

    case BeginEnd(expr) => {
      eval(env, mem, expr)
    }

    case FieldAccess(record, field) => eval(env, mem, record).v match {
      case r: RecordVal => {
        val temp = Search(r, field)
        Result(mem.get(temp.loc).get, mem)
      }
      case _ => throw UndefinedSemantics("No record val")
    }

    case FieldAssign(record, field, new_val) => {
      val res = eval(env, mem, record)
      res.v match {
        case r: RecordVal => {
          val temp = Search(r, field)
          val new_value = eval(env, res.m, new_val)
          Result(new_value.v, new_value.m.updated(temp.loc, new_value.v).get)
        }
        case _ => throw UndefinedSemantics("Undefined type")
      }
    }

    case Block(f, s) => {                         // same with other
      val temp1 = eval(env, mem, f) 
      eval(env, temp1.m, s)
    }

    // case PCallV(ftn, arg) => eval(env, mem, ftn).v match {
    //   case (x: ProcVal) => {
    //     val new_env_and_mem = ExtendMemAndEnv(x.args, env, mem, arg, arg, 0)
    //     eval(new_env_and_mem._1, new_env_and_mem._2, x.expr)
    //   }
    //   case _ => throw UndefinedSemantics("Type error occur")
    // }

    case PCallR(ftn, arg) => eval(env, mem, ftn).v match {
      case (x: ProcVal) => {
        val new_env = ExtendEnv(x.args, x.args, arg, env,0)
        eval(new_env, mem, x.expr)
      }
      case _ => throw UndefinedSemantics("Type error occur")
    }

    case PCallV(ftn, arg) => 
      val eftn = eval(env, mem, ftn)
      eftn match{
        case Result(v: ProcVal, m1: Mem) => 
          val resultlist = LoopEval(env, eftn.m, arg)
          val env_memlist = LoopAssign(v.args, resultlist, v.env, FindMem(resultlist))
          val env_mem = FindMemEnv(env_memlist)
          env_mem match{
            case (e: Env, m: Mem) => eval(e, m, v.expr)
          }
        case _ => throw new UndefinedSemantics("Type error occur")
      }

    case WhileExpr(cond, body) => eval(env, mem, cond).v match {
      case v: BoolVal => {
        if (v.b) {
          val res = eval(env, mem, body)
          eval(env, res.m, WhileExpr(cond, expr))
        } else {
          Result(SkipVal, mem)
        }
      }
      case _ => throw UndefinedSemantics("Not a bool value")
    }

    case EmptyRecordExpr => {
      Result(EmptyRecordVal, mem)
    }

    case RecordExpr(field, initVal, next) => {
      val init = eval(env, mem, initVal)
      val temp = init.m.extended(init.v)
      val nextR = eval(env, temp._1, next)
      nextR.v match {
        case r: RecordVal => Result((RecordVal(field, temp._2, r)), nextR.m)
        case EmptyRecordVal => Result((RecordVal(field, temp._2, EmptyRecordVal)), temp._1)
        case _ => throw UndefinedSemantics("Undefined val")
      }
    }


    case _ => throw UndefinedSemantics("Not Defined")
  }

  // def gc(env: Env, mem: Mem): Mem = {
  //   Mem(mem.m, mem.top)
  // }

  def addFromRecord(r: RecordValLike, mem: Mem, hashmem: HashMap[LocVal, Val]): HashMap[LocVal, Val] = {
    r match {
      case EmptyRecordVal => hashmem
      case r: RecordVal => {
        val new_mem = hashmem + (r.loc -> mem.get(r.loc).get)
        val new_new_mem = addFromLoc(new_mem, mem, mem.get(r.loc).get)
        addFromRecord(r.next, mem, new_new_mem)
      }
    }
  }

  def addFromLoc(new_h: HashMap[LocVal, Val], mem: Mem, v: Val): HashMap[LocVal, Val] = {
    v match {
      case l: LocVal => {
        val new_hash = new_h + (l -> mem.get(l).get)
        addFromLoc(new_hash, mem, mem.get(l).get)
      }
      case r: RecordVal => {
        addFromRecord(r, mem, new_h)
      }
      case p: ProcVal => {
        reach(p.env, mem, new_h, p.env.keySet.toList)
      }
      case _ => new_h
    }
  }

  def reach(env: Env, mem: Mem, new_h: HashMap[LocVal, Val], vars: List[Var]): HashMap[LocVal, Val] = {
    vars match {
      case Nil => new_h
      case ::(head, next) => {
        val res = mem.get(env(head)).get
        val new_hash = new_h + (env(head) -> res)
        res match {
          case r: RecordVal => {
            val new_new_hash = addFromRecord(r, mem, new_hash)
            reach(env, mem, new_new_hash, next)
          }
          case p: ProcVal => {
            val new_new_hash = reach(p.env, mem, new_hash, p.env.keySet.toList)
            reach(env, mem, new_new_hash, next)
          }
          case l: LocVal => {
            val new_new_hash = addFromLoc(new_hash, mem, l)
            reach(env, mem, new_new_hash, next)
          }
          case _ => reach(env, mem, new_hash, next)
        }

      }
    }
  }

  def gc(env: Env, mem: Mem): Mem = {
    if (env.isEmpty) Mem(mem.m.empty, mem.top)
    val vars = env.keySet.toList
    val new_mem_hash = reach(env, mem, HashMap[LocVal, Val](), vars)
    Mem(new_mem_hash, mem.top)
  }

  def apply(program: String): (Val, Mem) = {
    val parsed = MiniCParserDriver(program)
    val res = eval(new Env(), Mem(new HashMap[LocVal,Val],0), parsed)
    (res.v, res.m)
  }

}



object Hw4App extends App {
  
  println("Hello from Hw4!")

}