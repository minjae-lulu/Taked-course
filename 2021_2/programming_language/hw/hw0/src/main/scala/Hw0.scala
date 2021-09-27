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

object Hw0 extends App {
    def eval(f: Formula): Boolean = f match{
        case True => true
        case False => false
        case Not(f) => if(f==False) true else false
        case Andalso(left, right) => if(eval(left) && eval(right)) true else false
        case Orelse(left, right) => if(eval(left)==false && eval(right)==false) false else true
        case Implies(left, right) => if(eval(left)==true && eval(right)==false) false else true
    }
}


//   def eval(f: Formula): Boolean =
//     f match {
//       case True  => true
//       case False => false
//       case Not(f) =>  if(f==False)  true else  false
//       case Andalso(left, right) => if(eval(left)&&eval(right))  true else  false
//       case Orelse(left, right) => if(eval(left)==false && eval(right)==false)  false else  true
//       case Implies(left, right) => if(eval(left)==true&&eval(right)==false)  false else  true
//   }