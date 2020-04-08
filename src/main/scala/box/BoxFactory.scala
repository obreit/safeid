package box

import shapeless.{::, Generic, HNil}

trait BoxFactory[B <: Box] {
  def create(repr: B#Repr): Valid[B]
}
object BoxFactory {
  def apply[B <: Box: BoxFactory]: BoxFactory[B] = implicitly[BoxFactory[B]]

  def instance[B <: Box](f: B#Repr => Valid[B]): BoxFactory[B] = f(_)

  // creating an instance for any 'object' --> is this even necessary/useful?
  implicit def boxObjectCstr[B <: Box](implicit G: GenericForObject[B]): BoxFactory[B] = instance[B] { repr =>
    val expected = G.from(HNil)
    if(expected.repr == repr) Right(expected)
    else Left(s"Given '$repr' does not match expected '$expected'")
  }

 /*  create an instance for any one-field case class
   note that a 'sealed abstract case class' doesn't have a generic implicit!
  TODO typetag might add nice info, but is maybe too slow

   Note that this is in scope by default for every single-value case class that extends Box!!!
   This can be overriden with a memoized version in the companion of the specific case class!!

  TODO this is more 'functional' --> default convention should be that one doesn't throw
  if a subclass needs to check some predicates during construction it should provide an instance of boxfactory
  */
  implicit def defaultFactory[B <: Box](implicit G: Generic.Aux[B, B#Repr :: HNil]): BoxFactory[B] =
    instance[B](repr => Right(G.from(repr :: HNil)))
}
