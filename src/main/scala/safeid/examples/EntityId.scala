package safeid.examples

import java.util.UUID

import safeid.IdOf

sealed trait EntityId extends IdOf[UUID]

case class EntityId1(repr: UUID) extends EntityId
case class EntityId2(repr: UUID) extends EntityId