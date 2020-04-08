package examples

import box.TypedBox

case class BoxedString(repr: String) extends TypedBox[String] {
  require(repr.nonEmpty, "is empty")
}
