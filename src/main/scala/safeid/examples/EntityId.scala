package safeid.examples

import java.util.UUID

import safeid.TypedId

sealed trait EntityId extends TypedId[UUID]

case class EntityId1(repr: UUID) extends EntityId
case class EntityId2(repr: UUID) extends EntityId
