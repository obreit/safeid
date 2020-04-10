package json

import spray.json._
import box.{Box, BoxFactory, RefinedBox, RefinedBoxOf}
import eu.timepit.refined.api.{Refined, Validate}
import fp._
import eu.timepit.refined._
import shapeless.{::, Generic, HNil}
import box.syntax.BoxImplicits._

trait BoxFmt {

  implicit def boxFmt[B <: Box: BoxFactory](implicit ev: JsonFormat[B#Repr]): JsonFormat[B] =
    (jsonFmt[B] _) { js =>
      js.convertTo[B#Repr] |> Box.create[B] |> (_.fold(
        err => deserializationError(s"Couldn't deserialize '$js', due to: '$err'"),
        identity
      ))
    }(_ |> Box.value[B] |> (_.toJson))

  implicit def refinedFmt[T, P](implicit V: Validate[T, P],
                                ev: JsonFormat[T]): JsonFormat[T Refined P] =
    (jsonFmt[T Refined P] _) { js =>
      js.convertTo[T] |> refineV[P].apply[T] |>
        (_.fold(err => deserializationError(s"Couldn't deserialize '$js', due to: '$err'"), identity))
    }(_.value.toJson)

  implicit def refinedValueClassFmt[B <: RefinedBox](implicit ev: JsonFormat[Refined[B#T, B#P]],
                                                     G: Generic.Aux[B, Refined[B#T, B#P] :: HNil]): JsonFormat[B] = {
    println("INSIDE REFINED VALUE CLASS FMT")
    (jsonFmt[B] _)(_.convertTo[Refined[B#T, B#P]].toRef[B])(_ |> G.to |> (_.head.toJson))
  }
}
object BoxFmt extends BoxFmt
