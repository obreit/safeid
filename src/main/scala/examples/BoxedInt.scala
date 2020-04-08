package examples

import box.{BoxFactory, BoxOf}

sealed abstract class BoxedIntSuper(val repr: Int) extends BoxOf[Int]
case class BoxedInt(i: Int) extends BoxedIntSuper(i)
object BoxedInt {
  implicit val f: BoxFactory[BoxedInt] = BoxFactory.instance {
    case i if i >= 0 => Right(BoxedInt(i))
    case i => Left(s"Given int '$i' is not greater than 0")
  }
}
