import java.util.UUID

import adts.Alert.AlertState
import adts.Alert.AlertState.{AlertClear, AlertSet}
import adts.{BoxedInt, BoxedString}
import box.Box
import box.syntax.BoxImplicits._
import json.CustomProtocol._
import safeid.Id
import safeid.examples.{DeviceId, EntityId1, UserId}
import spray.json._

object Main extends App {

  // check ids
  println("Check Ids")
  println("-" * 100)

  val userId = Id.random[UserId]
  println(userId)
  println(userId == userId)

  val userId2 = Id.random[UserId]
  println(userId2 == userId)

  val deviceId = Id.random[DeviceId]
  println(deviceId)
  println(deviceId == userId)

  def fun(id: UserId): Unit = println(id)
  // fun(deviceId) --> doesn't compile

  println()

  // check adts and case classes
  println("Check ADTs + case classes")
  println("-" * 100)

  println(Box.create[BoxedString](""))
  println(Box.create[BoxedInt](42))
  println(Box.create[AlertState]("otherss"))
  println(Box.create[AlertState]("set") == Box.create[AlertState]("set"))
  println(Box.create[AlertState]("set") == Box.create[AlertState]("clear"))

  println(Id.random[EntityId1] == Id.random[EntityId1])

  val eid = Id.random[EntityId1]
  println(Id.value(eid))

  val uuid = UUID.randomUUID()
  val x = Box.create[DeviceId](uuid)
  val y = Id.create[DeviceId](uuid)
  println(x == y)

  println()

  // check implicit syntax
  println("Check implicit syntax")
  println("-" * 100)

  println("ads".unsafeBox[BoxedString])
  println("clear".box[AlertState])
  println("set".box[AlertState])
  println("blaaà.".box[AlertState])
  println(uuid.box[DeviceId])

  println(x.map(_.castTo[UserId]))

  println()

  // check json
  println("Check json")
  println("-" * 100)

  println(42.unsafeBox[BoxedInt].toJson.convertTo[BoxedInt])
  println("set".toJson.convertTo[AlertState])

  println()

  // Check unapply support
  println("Check unapply")
  println("-" * 100)

  println(AlertState.unapply("other"))

  def printUnapply(s: String): Unit = s match {
    case AlertState(AlertSet) => println("MATCH Set")
    case AlertState(AlertClear) => println("MATCH Clear")
    case AlertState(state) => println(s"Match other state: $state")
    case s => println(s"Match Other string $s")
  }

  printUnapply("set")
  printUnapply("clear")
  printUnapply("other")
  printUnapply("asdasd")

  println()

  // check factory constraints
  println("Check factory constraints")
  println("-" * 100)
  println(Box.create[DeviceId](uuid))
  println(Box.unsafe[DeviceId](uuid))
  println(Box.value[DeviceId](Id.random[DeviceId]))

  // doesn't compile (as required)
/*
  println(Id.create[AlertState]("clear"))
  println(Id.random[AlertState])
  println(Id.value[AlertState]("clear".box[AlertState]))
  println(Id.unsafe[AlertState]("clear"))
*/

  println()

  // check automatic derivation
  println("Check automatic derivation")
  println("-" * 100)

  import box.auto._

  println(Box.create[AlertState]("clear"))
  println(Box.create[AlertSet.type]("set"))
  println(AlertSet.toJson.convertTo[AlertSet.type])
  println(Box.create[AlertState]("set"))
  println(Box.create[DeviceId](uuid))
  println(Box.create[BoxedInt](42).toJson.convertTo[BoxedInt])

  import safeid.auto._

  println(Id.random[DeviceId].toJson.convertTo[DeviceId])
}
