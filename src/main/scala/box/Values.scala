package box

import shapeless.{:+:, CNil, Coproduct, HNil}

// This trait allows to collect all instances of a sealed hierarchy into a seq
// All elements of the hierarchy have to be objects -- is a class in the hierarchy this won't work
trait Values[B <: Box, C <: Coproduct] {
  def values: Map[B#Repr, B]
}
object Values {
  def instance[B <: Box, C <: Coproduct](vs: Map[B#Repr, B]): Values[B, C] = new Values[B, C] {
    //println(s"IN CONS $vs")
    override def values: Map[B#Repr, B] = vs
  }

  implicit def cNilValues[B <: Box]: Values[B, CNil] = instance(Map())
  implicit def cConsValues[B <: Box, H <: B, T <: Coproduct](implicit O: GenericForObject[H],
                                                             T: Values[B, T]): Values[B, H :+: T] =
    instance {
      val b = O.from(HNil)
      T.values + (b.repr -> b)
    }
}
