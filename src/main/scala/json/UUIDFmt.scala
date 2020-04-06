package json

import java.util.UUID

import spray.json.DefaultJsonProtocol._
import spray.json._

import scala.util.Try

trait UUIDFmt {

  implicit val uuidFmt: JsonFormat[UUID] =
    (jsonFmt[UUID] _ ) { js =>
      val str = js.convertTo[String]
      Try(UUID.fromString(str)).fold(err => deserializationError(s"Couldn't create UUID from string", err), identity)
    }(_.toString.toJson)
}
