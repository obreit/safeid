package safeid.examples

import java.util.UUID

import box.BoxFactory
import safeid.IdOf

case class AutomaticDerivId(repr: UUID) extends IdOf[UUID]
object AutomaticDerivId {
  // this should be selected over the default BoxFactory by default
  // --> the instance creation should be only called once
  implicit val f: BoxFactory[AutomaticDerivId] = BoxFactory.boxCaseClassCstr[AutomaticDerivId]
}
