package examples

import box.{BoxFactory, TypedBox}

case class AutomaticDerivTest(repr: Int) extends TypedBox[Int]
object AutomaticDerivTest {
  // this should be selected over the default BoxFactory by default
  // --> the instance creation should be only called once
  implicit val f: BoxFactory[AutomaticDerivTest] = BoxFactory.boxCaseClassCstr[AutomaticDerivTest]
}
