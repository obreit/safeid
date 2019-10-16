package safeid.examples

import java.util.UUID

import safeid.{Id, IdFactory, TypedId}
import safeid.constructing.IdConstructor

case class DeviceId(underlying: UUID) extends TypedId[UUID]
object DeviceId {
  implicit val constr: IdConstructor[DeviceId] = IdConstructor[DeviceId](DeviceId.apply, _.underlying)
  implicit val fact: IdFactory[DeviceId] = Id.factory[DeviceId]
}
