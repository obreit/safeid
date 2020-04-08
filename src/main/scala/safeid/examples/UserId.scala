package safeid.examples

import java.util.UUID

import safeid.IdOf

case class UserId(repr: UUID) extends IdOf[UUID]
