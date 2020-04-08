package examples

import box.BoxOf

sealed abstract class BoxedIntSuper(val repr: Int) extends BoxOf[Int]
case class BoxedInt(i: Int) extends BoxedIntSuper(i) {
  require(repr == 42, "needs to be 42")
}
