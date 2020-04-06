package adts

import box.{Box, BoxFactory, TypedBox}

case class BoxedString(repr: String) extends TypedBox[String] {
  require(repr.nonEmpty, "is empty")
}
object BoxedString {
  implicit val f: BoxFactory[BoxedString] = Box.factory[BoxedString]
}
