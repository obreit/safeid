package safeid.examples

import java.util.UUID

import safeid.{Id, IdFactory, TypedId}

case class UserId(repr: UUID) extends TypedId[UUID]
object UserId {
  implicit val f: IdFactory[UserId] = Id.factory[UserId]
}
