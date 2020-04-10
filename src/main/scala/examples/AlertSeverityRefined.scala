package examples

import box.RefinedBoxOf
import eu.timepit.refined.api.Refined
import eu.timepit.refined.boolean.And
import eu.timepit.refined.numeric.{GreaterEqual, LessEqual}
import examples.AlertSeverityRefined.ZeroToSeven

case class AlertSeverityRefined(repr: Int Refined ZeroToSeven) extends RefinedBoxOf[Int, ZeroToSeven]
object AlertSeverityRefined {
  type ZeroToSeven = GreaterEqual[0] And LessEqual[7]
}
