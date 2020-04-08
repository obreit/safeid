package safeid.examples

import java.util.UUID

import safeid.TypedId

case class UserId(repr: UUID) extends TypedId[UUID]
