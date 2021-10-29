package Hw2

import fastparse._
import MultiLineWhitespace._
import scala.collection.immutable.HashMap 

sealed trait Val
case class IntVal(n: Int) extends Val
case class BoolVal(b: Boolean) extends Val
case class ProcVal(v: Var, expr: Expr, env: Env) extends Val
case class RecProcVal(fv: Var, av: Var, body: Expr, expr: Expr, env: Env) extends Val

case class Env(hashmap: HashMap[Var,Val]) {
  def apply(variable: Var): Val = hashmap(variable)
  def exists(v: Var): Boolean = 
    hashmap.exists((a: (Var, Val)) => a._1 == v)
  def add(v: Var, value: Val) = Env(hashmap + (v -> value))
  
}

sealed trait Program
sealed trait Expr extends Program
case class Const(n: Int) extends Expr
case class Var(s: String) extends Expr
case class Add(l: Expr, r: Expr) extends Expr
case class Sub(l: Expr, r: Expr) extends Expr
case class Equal(l: Expr, r: Expr) extends Expr
case class Iszero(c: Expr) extends Expr
case class Ite(c: Expr, t: Expr, f: Expr) extends Expr
case class Let(name: Var, value: Expr, body: Expr) extends Expr
case class Paren(expr: Expr) extends Expr
case class Proc(v: Var, expr: Expr) extends Expr
case class PCall(ftn: Expr, arg: Expr) extends Expr
case class LetRec(fname: Var, aname: Var, fbody: Expr, ibody: Expr) extends Expr

sealed trait IntExpr
case class IntConst(n: Int) extends IntExpr
case object IntVar extends IntExpr
case class IntAdd(l: IntExpr, r: IntExpr) extends IntExpr
case class IntSub(l: IntExpr, r: IntExpr) extends IntExpr
case class IntMul(l: IntExpr, r: IntExpr) extends IntExpr
case class IntSigma(f: IntExpr, t: IntExpr, b: IntExpr) extends IntExpr
case class IntPi(f: IntExpr, t: IntExpr, b: IntExpr) extends IntExpr
case class IntPow(b: IntExpr, e: IntExpr) extends IntExpr


package object Hw2 {

}

object IntInterpreter {
  def evalInt(expr: IntExpr, env: Option[Int]): Int =
  expr match{
    case IntConst(n) => n
    case IntVar => env match{
      case None => throw new Exception("None")
      case Some(v) => v
    }

    case IntAdd(l,r) => 
    (evalInt(l,env), evalInt(r,env)) match{
      case (v1: Int, v2: Int) => v1 + v2
      case _ => throw new Exception("type error occur")
    }

    case IntSub(l,r) => 
    (evalInt(l,env), evalInt(r,env)) match{
      case (v1: Int, v2: Int) => v1 - v2
      case _ => throw new Exception("type error occur")
    }

    case IntMul(l,r) => 
    (evalInt(l,env), evalInt(r,env)) match{
      case (v1: Int, v2: Int) => v1 * v2
      case _ => throw new Exception("type error occur")
    }

    case IntSigma(f,t,b) => 
    (evalInt(f,env), evalInt(t,env)) match{
      case(v1: Int, v2: Int) => if(v1>v2) 0 else evalInt(IntSigma(IntConst(v1+1),t,b),env) + evalInt(b,Some(v1))
      case _ => throw new Exception("type error occur")
    }
    
    case IntPi(f,t,b) => 
    (evalInt(f,env), evalInt(t,env)) match{
      case(v1: Int, v2: Int) => if(v1>v2) 1 else evalInt(IntPi(IntConst(v1+1),t,b),env) * evalInt(b,Some(v1))
      case _ => throw new Exception("type error occur")
    }

    case IntPow(b,e) => 
    (evalInt(b,env), evalInt(e,env)) match{
      case (v1: Int, v2: Int) => if(v2 == 0) 1 else evalInt(IntMul(IntPow(b,IntConst(v2-1)),IntConst(v1)),env)
      case _ => throw new Exception("type error occur")
    }

    case _ => throw new Exception("No match case")
  }
  
  
  def apply(s: String): Int = {
    val parsed = IntParser(s)
    evalInt(parsed, None)
  }
}



object LetRecInterpreter {
  
  def eval(env: Env, expr: Expr): Val = expr match {
    case Const(n) => IntVal(n)
    case Var(s) => if(env.exists(Var(s))) env(Var(s)) else throw new Exception("No exist error")

    case Add(l,r) => 
    (eval(env,l), eval(env,r)) match{
      case(v1 : IntVal, v2: IntVal) => IntVal(v1.n + v2.n)
      case _ => throw new Exception("type error occur")
    }

    case Sub(l,r) =>
    (eval(env,l), eval(env,r)) match{
      case(v1 : IntVal, v2: IntVal) => IntVal(v1.n - v2.n)
      case _ => throw new Exception("type error occur")
    }

    case Equal(l,r) =>
    (eval(env,l), eval(env,r)) match{
      case(v1 : IntVal, v2: IntVal) => BoolVal(v1.n == v2.n)
      case _ => BoolVal(false)
    }

    case Iszero(c) =>
    eval(env,c) match{
      case (v1: IntVal) => BoolVal(v1.n == 0)
      case _ => BoolVal(false)
    }

    case Ite(c,t,f) =>
    eval(env,c) match{
      case (v1:BoolVal) => if(v1.b) eval(env,t) else eval(env,f)
      case _ => throw new Exception("type error occur")
    }

    case Let(name, value, body) => eval(env.add(name, eval(env, value)), body)
    case Paren(expr) => eval(env,expr) 
    case Proc(v, expr) => ProcVal(v,expr,env)
    
    case PCall(ftn,arg) =>
    (eval(env,ftn), eval(env,arg)) match{
      case(v1: ProcVal, v2: IntVal) => eval(v1.env, Let(v1.v, Const(v2.n), v1.expr))
      case(v1: RecProcVal, v2: IntVal) => eval(Env(env.hashmap.concat(v1.env.hashmap)), Let(v1.av, Const(v2.n), v1.body))
      case _ => throw new Exception("type error occur")
    }
    // {
    //   val ProcVal(param,body,env2) = eval(env,ftn)
    //   val v = eval(env,arg)
    //   eval(env2.add(param, v),body)
    // }
    
    case LetRec(fname, aname, fbody, ibody) => {
      eval(env.add(fname, RecProcVal(fname, aname, fbody, ibody, env)), ibody) match {
        case (rec : RecProcVal) => RecProcVal(fname, aname, fbody, ibody, env.add(fname, RecProcVal(fname, aname, fbody, ibody, env)))
        case _ => eval(env.add(fname, RecProcVal(fname, aname, fbody, ibody, env)), ibody)
      }
    }

    case _ => throw new Exception("No match case")
  }
  
  def apply(program: String): Val = {
    val parsed = LetRecParserDriver(program)
    eval(Env(new HashMap[Var,Val]()), parsed)
  }
}



object LetRecToString {
  def apply(expr: Expr): String =
  expr match {
    case Const(n) => s"${n}"
    case Var(s) => s
    case Add(l,r) => s"${apply(l)} + ${apply(r)}"
    case Sub(l,r) => s"${apply(l)} - ${apply(r)}"
    case Equal(l,r) => s"${apply(l)} == ${apply(r)}"
    case Iszero(c) => s"iszero ${apply(c)}"
    case Ite(c,t,f) => s"if ${apply(c)} then ${apply(t)} else ${apply(f)}"
    case Let(name, value, body) => s"let ${apply(name)} = ${apply(value)} in ${apply(body)}"
    case Paren(expr) =>   s"(${apply(expr)})" // add() x
    case Proc(v, expr) => s"proc ${apply(v)} ${apply(expr)}"
    case PCall(ftn,arg) => s"${apply(ftn)} ${apply(arg)}" // x
    case LetRec(fname,aname,fbody,ibody) => s"letrec ${apply(fname)}(${apply(aname)}) = ${apply(fbody)} in ${apply(ibody)}"
    case _ => throw new Exception("No match case")


  }
}


object Hw2App extends App {
  
  println("Hello from Hw2!")

  val int_prog = """x + 1"""
  val parsed = IntParser(int_prog)
  println(parsed)

  //println("ahah test test")
  //println(("1 + 1" == IntVal(2)))
}
