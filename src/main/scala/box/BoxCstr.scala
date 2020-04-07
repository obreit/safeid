package box

import shapeless.{:+:, ::, CNil, Coproduct, Generic, HNil, Inl, Inr}

import scala.util.Try
import scala.reflect.runtime.universe.TypeTag

// We cannot add a type bound here, otherwise 'coproductCstr: BoxCstr[H :+: T]' wouldn't work
trait BoxCstr[B] {
  type Repr

  def create(r: Repr): Valid[B]

  @throws[IllegalArgumentException]
  def unsafe(r: Repr): B
}

object BoxCstr {
  type Aux[B, Repr0] = BoxCstr[B] { type Repr = Repr0 }
  type GenericForObject[O] = Generic.Aux[O, HNil]

  def apply[B <: Box](implicit BC: Aux[B, B#Repr]): Aux[B, B#Repr] = BC

  def instance[B: TypeTag, Repr0](f: Repr0 => Valid[B]): BoxCstr.Aux[B, Repr0] = new BoxCstr[B] {
    override type Repr = Repr0

    println(s"Instance creation for: ${implicitly[TypeTag[B]].tpe}")
    override def create(r: Repr): Valid[B] = f(r)
    override def unsafe(r: Repr): B = f(r).fold(
      err => throw new IllegalArgumentException(err),
      identity
    )
  }

  // creating an instance for any 'object'
  implicit def objectCstr[B <: Box: TypeTag](implicit G: GenericForObject[B]): BoxCstr.Aux[B, B#Repr] =
    instance[B, B#Repr] { repr =>
      val expected = G.from(HNil)
      if(expected.repr == repr) Right(expected)
      else Left(s"Given '$repr' does not match expected '$expected'")
    }

  // create an instance for any one-field case class
  // note that a 'sealed abstract case class' doesn't have a generic implicit!
  //TODO typetag might add nice info, but is maybe too slow
  implicit def caseClassCstr[B <: Box: TypeTag](implicit G: Generic.Aux[B, B#Repr :: HNil]): BoxCstr.Aux[B, B#Repr] =
    instance[B, B#Repr] { repr =>
      Try(G.from(repr :: HNil)).toEither.left.map { ex =>
        s"Error parsing '$repr', due to ${ex.getMessage}"
      }
    }

  implicit def cNilCstr[Repr0]: BoxCstr.Aux[CNil, Repr0] = instance[CNil, Repr0] { repr =>
    Left(s"Couldn't parse '$repr'")
  }

  // Note this only compiles for sealed hierarchies consisting of only 'object's!!!
  // Note that there is a constraint that all subelements of the hierarchy have the same underlying type (H#Repr)
  implicit def cConsCstr[H <: Box: TypeTag, T <: Coproduct: TypeTag](implicit ev: GenericForObject[H],
                                                                     HC: BoxCstr.Aux[H, H#Repr],
                                                                     TC: BoxCstr.Aux[T, H#Repr]): BoxCstr.Aux[:+:[H,T], H#Repr] = {
    instance[H :+: T, H#Repr] { repr =>
      HC.create(repr).map(Inl(_)).left.flatMap(_ => TC.create(repr).map(Inr(_)))
    }
  }

  // create an instance for a sealed hierarchy
  implicit def sealedBoxCstr[B <: Box: TypeTag, C <: Coproduct](implicit G: Generic.Aux[B, C],
                                                                CC: BoxCstr.Aux[C, B#Repr]): BoxCstr.Aux[B, B#Repr] =
    instance(repr => CC.create(repr).map(G.from))

  // This trait allows to collect all instances of a sealed hierarchy into a seq
  // All elements of the hierarchy have to be objects -- is a class in the hierarchy this won't work
  trait Values[B, C <: Coproduct] {
    def values: Seq[B]
  }
  object Values {
    def instance[B, C <: Coproduct](vs: Seq[B]): Values[B, C] = new Values[B, C] {
      println(s"IN CONS $vs")
      override def values: Seq[B] = vs
    }

    implicit def cNilValues[B]: Values[B, CNil] = instance(Seq())
    implicit def cConsValues[B, H <: B, T <: Coproduct](implicit O: GenericForObject[H],
                                                        T: Values[B, T]): Values[B, H :+: T] =
      instance(O.from(HNil) +: T.values)
  }
}
