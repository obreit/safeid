import java.util.UUID

import box.{Box, RefinedBox}
import box.syntax.BoxImplicits._
import eu.timepit.refined.api.Refined
import examples.BigSealed._
import examples._
import json.CustomProtocol._
import safeid.Id
import safeid.examples.{AutomaticDerivId, DeviceId, EntityId1, UserId}
import spray.json._
import eu.timepit.refined.auto._
import examples.AlertSeverityRefined.ZeroToSeven
import shapeless.{::, Generic, HNil, Typeable}

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
  println(Box.create[BigSealed]("otherss"))
  println(Box.create[BigSealed]("set") == Box.create[BigSealed]("set"))
  println(Box.create[BigSealed]("set") == Box.create[BigSealed]("clear"))

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
  println("clear".box[BigSealed])
  println("a".box[BigSealed])
  println("set".box[BigSealed])
  println("blaaà.".box[BigSealed])
  println(uuid.box[DeviceId])

  println(x.map(_.castTo[UserId]))

  println()

  // check json
  println("Check json")
  println("-" * 100)

  println(42.unsafeBox[BoxedInt].toJson.convertTo[BoxedInt])
  println("1".toJson.convertTo[BigSealed])

  println()

  // Check unapply support
  println("Check unapply")
  println("-" * 100)

  println(BigSealed.unapply("q"))

  def printUnapply(s: String): Unit = s match {
    case BigSealed(A) => println("MATCH A")
    case BigSealed(B) => println("MATCH B")
    case BigSealed(bs) => println(s"Match other state: $bs")
    case s => println(s"Match Other string $s")
  }

  printUnapply("a")
  printUnapply("m")
  printUnapply("13")
  printUnapply("asdasd")

  println()

  // check factory constraints
  println("Check factory constraints")
  println("-" * 100)
  println(Box.create[DeviceId](uuid))
  println(Box.unsafe[DeviceId](uuid))
  //println(Box.value[DeviceId](Id.random[DeviceId]))

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

  println(Box.create[BigSealed]("p"))
  println(Box.create[A.type]("a"))
  println(A.toJson.convertTo[A.type])
  println(Box.create[D.type]("d"))
  println(Box.create[DeviceId](uuid))
  println(Box.create[BoxedInt](42).toJson.convertTo[BoxedInt])

  // calls the box factory constructor 3 times
  println(Id.random[DeviceId].toJson.convertTo[DeviceId])
  // calls the box factory constructor once
  println(Box.create[AutomaticDerivTest](1).toJson.convertTo[AutomaticDerivTest])
  // calls the box factory constructor once
  println(Id.random[AutomaticDerivId])
  println(Id.random[AutomaticDerivId].toJson.convertTo[AutomaticDerivId])

  println()
  // check from applier
  println("-" * 100)
  println(Box.create[Temperature](-101))
  println(Box.create[BoxedInt](-101))

  println(Box.unsafe[Temperature](50).hashCode)
  println(50.hashCode)

  println(50 == 50)
  println(50.unsafeBox[Temperature])

  println(Id.random[DeviceId])
  println(Id.random[DeviceId])
  println(Box.create[DeviceId](uuid))

  println()
  // Refined stuff
  println("-" * 100)


  import json.CustomProtocol._
  println(AlertSeverityRefined(1).toJson.convertTo[AlertSeverityRefined])

  println(Box.create[AlertSeverityRefined](7))

  val sev: Int Refined ZeroToSeven = 1
  println(AlertSeverityRefined(sev))
  println(sev.toRef[AlertSeverityRefined])

  println(5.toJson.convertTo[AlertSeverityRefined])
  println(AlertSeverityRefined(4).toJson)
}
