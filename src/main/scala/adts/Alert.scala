package adts

import box.{Box, BoxCstr, BoxFactory, FactorySupport, TypedBox, UnapplySupport}

object Alert {

  sealed abstract class AlertState(val repr: String) extends TypedBox[String]

  object AlertState extends UnapplySupport[AlertState] {
    implicit val f: BoxFactory[AlertState] = Box.factory[AlertState]

    override val values: Seq[AlertState] = V()

    case object AlertSet extends AlertState("set")
    case object AlertClear extends AlertState("clear")
    case object AlertOther extends AlertState("other")
  }
}