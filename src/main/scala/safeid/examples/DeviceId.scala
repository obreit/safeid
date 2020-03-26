package safeid.examples

import java.util.UUID

import safeid.{Id, IdFactory, TypedId}

case class DeviceId(underlying: UUID) extends TypedId[UUID]
object DeviceId {
  implicit val fact: IdFactory[DeviceId] = Id.factory[DeviceId]
}
