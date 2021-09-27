sealed trait IntList
case object Nil extends IntList
case class Cons(v: Int, t: IntList) extends IntList

sealed trait BTree
case object Leaf extends BTree
case class IntNode(v: Int, left: BTree, right: BTree)
extends BTree

sealed trait Formula
case object True extends Formula
case object False extends Formula
case class Not(f: Formula) extends Formula
case class Andalso(left: Formula, right: Formula) extends Formula
case class Orelse(left: Formula, right: Formula)  extends Formula
case class Implies(left: Formula, right: Formula) extends Formula

object Hw1 extends App {

  println("Hw1!")

  def gcd(a: Int, b: Int): Int = {  //using euclid algorithm
    if (b == 0) a
    else gcd(b, a%b)
  }

  def oddSum(f: Int=>Int, n: Int): Int = {
    val temp = if (n%2==0) n-1 else n // make val temp to odd
    if (n<1) 0
    else f(temp) + oddSum(f, temp-2)
  }

  def foldRight(init: Int, ftn: (Int, Int)=>Int, list: IntList): Int = list match{
    case Nil => init
    case Cons(h,t) => ftn(foldRight(init, ftn, t),h)

  }

  def map(f: Int=>Int, list: IntList): IntList = list match {
    case Nil => Nil
    case Cons(h,t) => Cons(f(h),map(f,t))
  }

  def iter[A](f: A => A, n: Int): A => A = {
    if(n<1) (x: A) => x
    else (x: A) => f(iter(f,n-1)(x))
  }

  def insert(t: BTree, a: Int): BTree = t match {
    case Leaf => IntNode(a,Leaf,Leaf)
    case IntNode(t,left,right) => if(a<t) IntNode(t, insert(left, a), right) else IntNode (t,left,insert(right,a))
  }

  def eval(f: Formula): Boolean = f match{
    case True => true
    case False => false
    case Not(f) => if(f==False) true else false
    case Andalso(left, right) => if(eval(left) && eval(right)) true else false
    case Orelse(left, right) => if(eval(left)==false && eval(right)==false) false else true
    case Implies(left, right) => if(eval(left)==true && eval(right)==false) false else true
  }

}