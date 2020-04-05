package adts

import box.BoxCstr._
import box.{Box, BoxCstr, BoxFactory, FactorySupport, TypedBox, UnapplySupport}
import shapeless.{Coproduct, Generic}

object Alert {

  sealed abstract class AlertState(val repr: String) extends TypedBox[String]

  val x = BoxCstr[AlertState]
  object AlertState extends FactorySupport[AlertState](x) with UnapplySupport[AlertState] {
    // caches the implicit
    //implicit val f: BoxFactory[AlertState] = Box.factory[AlertState]

    override val values: Seq[AlertState] = V()

    case object AlertSet extends AlertState("set")
    case object AlertClear extends AlertState("clear")
    case object AlertOther extends AlertState("other")
  }
}