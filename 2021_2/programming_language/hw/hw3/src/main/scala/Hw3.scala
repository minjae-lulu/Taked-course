package hw3

import scala.collection.immutable.HashMap 
import hw3._


package object hw3 {
  type Env = HashMap[Var,Val]
  type Loc = Int
  
}

case class Mem(m: HashMap[Loc,Val], top: Loc) {
  def exists(v: Val): Boolean =
    m.exists((a: (Loc, Val)) => a._2 == v)
  def add(v: Loc, value: Val) = Mem(m + (v -> value),v)
}

sealed trait Val
case class IntVal(n: Int) extends Val
case class IntListVal(n: List[IntVal]) extends Val
case class BoolVal(b: Boolean) extends Val
case class ProcVal(v: Var, expr: Expr, env: Env) extends Val
case class RecProcVal(fv: Var, av: Var, body: Expr, env: Env) extends Val
case class LocVal(l: Loc) extends Val


sealed trait Program
sealed trait Expr extends Program
case class ConstI(n: Int) extends Expr
case class ConstB(n: Boolean) extends Expr
case class ConstIL(n: List[IntVal]) extends Expr
case class Var(s: String) extends Expr
case class Add(l: Expr, r: Expr) extends Expr
case class Sub(l: Expr, r: Expr) extends Expr
case class Mul(l: Expr, r: Expr) extends Expr
case class Div(l: Expr, r: Expr) extends Expr
case class Rem(l: Expr, r: Expr) extends Expr
case class Cons(l: Expr, r: Expr) extends Expr
case class GTExpr(l: Expr, r: Expr) extends Expr
case class GEQExpr(l: Expr, r: Expr) extends Expr
case class Iszero(c: Expr) extends Expr
case class Ite(c: Expr, t: Expr, f: Expr) extends Expr
case class ValExpr(name: Var, value: Expr, body: Expr) extends Expr
case class VarExpr(name: Var, value: Expr, body: Expr) extends Expr
case class Proc(v: Var, expr: Expr) extends Expr
case class DefExpr(fname: Var, aname: Var, fbody: Expr, ibody: Expr) extends Expr
case class Asn(v: Var, e: Expr) extends Expr
case class Paren(expr: Expr) extends Expr
case class Block(f: Expr, s: Expr) extends Expr
case class PCall(ftn: Expr, arg: Expr) extends Expr







object MiniScalaInterpreter {

  case class Result(v: Val, m: Mem)
  case class UndefinedSemantics(msg: String = "", cause: Throwable = None.orNull) extends Exception("Undefined Semantics: " ++ msg, cause)
  
  
  def eval(env: Env, mem: Mem, expr: Expr): Result = expr match {
    case ConstI(n) => Result(IntVal(n), mem)
    case ConstB(n) => Result(BoolVal(n), mem)
    case ConstIL(n) => Result(IntListVal(n), mem)

    case Var(s) =>
      if(env.exists((a : (Var, Val)) => a._1 == Var(s))) {
        val temp = env(Var(s))
        temp match {
          case LocVal(1) => Result(mem.m.getOrElse(1,throw UndefinedSemantics("Problem")), mem)
          case _ => Result(temp, mem)
        }
      }
      else{
        throw UndefinedSemantics("error occur")
      }
    
    
    case Add(l, r) => (eval(env,mem,l).v, eval(env,mem,r).v) match {
      case (x: IntVal, y: IntVal) => {
        val temp1 = mem.add(mem.top+1,x)
        val temp2 = mem.add(temp1.top+1,y)
        Result(IntVal(x.n + y.n), temp2)
      }
      case _ => throw UndefinedSemantics("Type error occur")
    }

    case Sub(l,r) => (eval(env,mem,l).v, eval(env,mem,r).v) match {
      case (x: IntVal, y: IntVal) => {
        val temp1 = mem.add(mem.top+1,x)
        val temp2 = mem.add(temp1.top+1,y)
        Result(IntVal(x.n - y.n), temp2)
      }
      case _ => throw UndefinedSemantics("Type error occur")
    }

    case Mul(l,r) => (eval(env,mem,l).v, eval(env,mem,r).v) match {
      case (x: IntVal, y: IntVal) => {
        val temp1 = mem.add(mem.top+1,x)
        val temp2 = mem.add(temp1.top+1,y)
        Result(IntVal(x.n * y.n), temp2)
      }
      case _ => throw UndefinedSemantics("Type error occur")
    }
    
    case Div(l,r) => (eval(env,mem,l).v, eval(env,mem,r).v) match {
      case (x: IntVal, y: IntVal) => {
        val temp1 = mem.add(mem.top+1,x)
        val temp2 = mem.add(temp1.top+1,y)
        Result(IntVal(x.n / y.n), temp2)
      }
      case _ => throw UndefinedSemantics("Type error occur")
    }
    
    // case Cons(l,r) => (eval(env,mem,l).v, eval(env,mem,r).v) match {
    //   case (x: IntVal, y: IntVal) => {
    //     val temp1 = mem.add(mem.top+1,x)
    //     val temp2 = mem.add(temp1.top+1,y)
    //     Result(IntVal(x.n :: y.n), temp2)
    //   }
    //   case _ => throw UndefinedSemantics("Type error occur")
    // }
    
    case Rem(l,r) => (eval(env,mem,l).v, eval(env,mem,r).v) match {
      case (x: IntVal, y: IntVal) => {
        val temp1 = mem.add(mem.top+1,x)
        val temp2 = mem.add(temp1.top+1,y)
        Result(IntVal(x.n % y.n), temp2)
      }
      case _ => throw UndefinedSemantics("Type error occur")
    }

    case GTExpr(l,r) => (eval(env,mem,l).v, eval(env,mem,r).v) match {
      case (x: IntVal, y: IntVal) => {
        val temp1 = mem.add(mem.top+1,x)
        val temp2 = mem.add(temp1.top+1,y)
        Result(BoolVal(x.n > y.n), temp2)
      }
      case _ => throw UndefinedSemantics("Type error occur")
    }

    case GEQExpr(l,r) => (eval(env,mem,l).v, eval(env,mem,r).v) match {
      case (x: IntVal, y: IntVal) => {
        val temp1 = mem.add(mem.top+1,x)
        val temp2 = mem.add(temp1.top+1,y)
        Result(BoolVal(x.n > y.n), temp2)
      }
      case _ => throw UndefinedSemantics("Type error occur")
    }

    case Iszero(c) => eval(env,mem,c).v match {
      case (x: IntVal) => {
        val temp1 = mem.add(mem.top+1,x)
        Result(BoolVal(x.n == 0), temp1)
      }
      case _ => throw UndefinedSemantics("Type error occur")
    }

    case Ite(c,t,f) => eval(env,mem,c).v match {
      case v: BoolVal => {
        val temp1 = mem.add(mem.top+1, v)
        if (v.b) eval(env, temp1, t)
        else eval(env,temp1,f)
      }
      case _ => throw UndefinedSemantics("Type error occur")
    }

    case ValExpr(name, value, body) => {
      val temp1 = eval(env, mem, value)
      val new_env = env + (name -> eval(env,mem,value).v)
      eval(new_env, temp1.m, body)
    }

    case VarExpr(name, value, body) => {
      val temp1 = eval(env, mem, value)
      val temp2 = mem.add(mem.top+1, temp1.v)
      val new_env = env + (name -> LocVal(temp2.top))
      eval(new_env, temp2, body)
    }

    case Proc(v, expr) => {
      Result(ProcVal(v,expr,env), mem)
    }

    case DefExpr(fname, aname, fbody, ibody) => {
      val recprocval = RecProcVal(fname, aname, fbody, env)
      val temp = env + (fname -> recprocval)
      val tempmem = mem.add(mem.top+1, recprocval)
      eval(temp,tempmem, ibody)
    }

    case Asn(v,e) => {
      val temp2 = eval(env,mem,e).v
      v match{
        case Var(s) => {
          if(env.exists((a: (Var, Val)) => a._1 == Var(s))) {
            env(Var(s)) match {
              case LocVal(1) => {
                val temp3 = mem.add(1, temp2)
                Result(temp2, temp3)
              }
              case _ => throw UndefinedSemantics("Reassignment to Val")
            }
          }
          else{
            throw UndefinedSemantics("No such variable")
          }
        }
      }
    }

    case Paren(expr) => eval(env,mem,expr)

    case Block(f,s) => {
      val temp1 = eval(env,mem,f)
      eval(env,temp1.m,s)
    }

    case PCall(ftn, expr) => eval(env,mem,ftn).v match{
      case(x: ProcVal) => {
        val tempmem = mem.add(mem.top+1,x)
        val args = eval(env, tempmem, expr).v
        val tempmem2 = tempmem.add(tempmem.top+1,args)
        val temp = x.env + (x.v -> args)
        eval(temp,tempmem2,x.expr)
      }
      case(x: RecProcVal) => {
        val tempmem = mem.add(mem.top+1,x)
        val args = eval(env, tempmem, expr).v
        val tempmem2 = tempmem.add(tempmem.top+1,args)
        val temp2 = x.env + (x.av -> args) + (x.fv -> x)
        eval(temp2, tempmem2, x.body)
      }
      case _ => throw UndefinedSemantics("Type error occur")
    }

    //case _ => throw UndefinedSemantics("Not Defined Case")


  }
  
  def apply(program: String): Val = {
    val parsed = MiniScalaParserDriver(program)
    eval(new Env(), Mem(new HashMap[Loc,Val],0), parsed).v
  }

}


object Hw3App extends App {
  
  println("Hello from Hw3!")

}