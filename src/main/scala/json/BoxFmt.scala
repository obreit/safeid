package json

import spray.json._
import box.{Box, BoxFactory}
import fp._

trait BoxFmt {

  implicit def boxFmt[B <: Box: BoxFactory](implicit ev: JsonFormat[B#Repr]): JsonFormat[B] =
    (jsonFmt[B] _) { js =>
      js.convertTo[B#Repr] |> Box.create[B] |> (_.fold(
        err => deserializationError(s"Couldn't deserialize '$js', due to: '$err'"),
        identity
      ))
    }(_ |> Box.value[B] |> (_.toJson))
}
object BoxFmt extends BoxFmt
