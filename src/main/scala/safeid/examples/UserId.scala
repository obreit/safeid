package safeid.examples

import java.util.UUID

import safeid.constructing.IdConstructor
import safeid.{Id, IdFactory, TypedId}

case class UserId(underlying: UUID) extends TypedId[UUID]
object UserId {
  implicit val constr: IdConstructor[UserId] = IdConstructor[UserId](UserId.apply, _.underlying)

  implicit val fact: IdFactory[UserId] = Id.factory[UserId]
}
