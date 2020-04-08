package examples

import box.BoxOf

case class BoxedString(repr: String) extends BoxOf[String] {
  require(repr.nonEmpty, "is empty")
}
