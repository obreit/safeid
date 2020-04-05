package adts

import box.{Box, BoxFactory, TypedBox}

sealed abstract class BoxedIntSuper(val repr: Int) extends TypedBox[Int]
case class BoxedInt(i: Int) extends BoxedIntSuper(i) {
  require(repr == 42, "needs to be 42")
}
