package safeid.examples

import java.util.UUID

import safeid.{Id, IdFactory, TypedId}

case class UserId(underlying: UUID) extends TypedId[UUID]
object UserId {
  implicit val fact: IdFactory[UserId] = Id.factory[UserId]
}
