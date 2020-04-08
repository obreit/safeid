package examples

import box.{BoxFactory, BoxOf}

case class AutomaticDerivTest(repr: Int) extends BoxOf[Int]
object AutomaticDerivTest {
  // this should be selected over the default BoxFactory by default
  // --> the instance creation should be only called once
  implicit val f: BoxFactory[AutomaticDerivTest] = BoxFactory.boxCaseClassCstr[AutomaticDerivTest]
}
