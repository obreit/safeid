package safeid.examples

import java.util.UUID

import safeid.{Id, IdFactory, TypedId}

sealed trait EntityId extends TypedId[UUID]

case class EntityId1(repr: UUID) extends EntityId
object EntityId1 {
  implicit val f: IdFactory[EntityId1] = Id.factory[EntityId1]
}
case class EntityId2(repr: UUID) extends EntityId
object EntityId2 {
  implicit val f: IdFactory[EntityId2] = Id.factory[EntityId2]
}