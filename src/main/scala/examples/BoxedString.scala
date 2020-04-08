package examples

import box.{BoxFactory, BoxOf}

case class BoxedString(repr: String) extends AnyVal with BoxOf[String]
object BoxedString {
  implicit val f: BoxFactory[BoxedString] = BoxFactory.instance {
    case "" => Left("String cannot be empty")
    case s => Right(BoxedString(s))
  }
}
