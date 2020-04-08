package safeid.examples

import java.util.UUID

import safeid.IdOf

case class DeviceId(repr: UUID) extends AnyVal with IdOf[UUID]
