package safeid.examples

import java.util.UUID

import safeid.{Id, IdFactory, TypedId}

case class DeviceId(repr: UUID) extends TypedId[UUID]
object DeviceId {
  implicit val f: IdFactory[DeviceId] = Id.factory[DeviceId]
}
