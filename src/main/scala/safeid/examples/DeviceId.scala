package safeid.examples

import java.util.UUID

import safeid.TypedId

case class DeviceId(repr: UUID) extends TypedId[UUID]
