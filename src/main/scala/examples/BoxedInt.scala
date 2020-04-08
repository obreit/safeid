package examples

import box.TypedBox

sealed abstract class BoxedIntSuper(val repr: Int) extends TypedBox[Int]
case class BoxedInt(i: Int) extends BoxedIntSuper(i) {
  require(repr == 42, "needs to be 42")
}
