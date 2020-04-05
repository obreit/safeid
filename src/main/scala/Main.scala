import java.util.UUID

import adts.Alert.AlertState
import adts.Alert.AlertState.{AlertClear, AlertOther, AlertSet}
import adts.{BoxedInt, BoxedString}
//import box.BoxCstr.Values
import box.{Box, BoxCstr, TypedBox}
import safeid.Id
import spray.json._
import json.CustomProtocol._
import safeid.examples.{DeviceId, EntityId1, UserId}
//import box.auto._
//import safeid.auto._
import box.syntax.BoxImplicits._
import shapeless.{:+:, CNil, Generic}

object Main extends App {

//  // check ids
//  println("-" * 100)
//
//  val userId = Id.random[UserId]
//  println(userId)
//  println(userId == userId)
//
//  val userId2 = Id.random[UserId]
//  println(userId2 == userId)
//
//  val deviceId = Id.random[DeviceId]
//  println(deviceId)
//  println(deviceId == userId)
//
//  def fun(id: UserId): Unit = println(id)
//  // fun(deviceId) --> doesn't compile
//
//  // check adts and case classes
//  println("-" * 100)
//
//  println("ads".unsafeBox[BoxedString])
//  println(Box.create[BoxedString](""))
//  println(Box.create[BoxedInt](42))
//  println(Box.create[AlertState]("otherss"))
//  println(Box.create[AlertState]("set").getOrElse(()) == Box.create[AlertState]("set").getOrElse(()))
//  println(Box.create[AlertState]("set").getOrElse(()) == Box.create[AlertState]("clear").getOrElse(()))
//
//  println(Id.random[EntityId1] == Id.create[EntityId1](UUID.randomUUID()))
//
//  println(s"UUID TO BOX: ${UUID.randomUUID().box[DeviceId]}")
//
//  val eid = Id.random[EntityId1]
//  println(Id.value(eid))
//
//  val uuid = UUID.randomUUID()
//  val x = Box.create[DeviceId](uuid)
//  val y = Id.create[DeviceId](uuid)
//  println(x == y)
//
//  // check implicit syntax
//  println("-" * 100)
//
//  println("clear".box[AlertState])
//  println("set".box[AlertState])
//  println("set".box[AlertSet.type])
//  println("blaaà.".box[AlertState])
//  println("clear".box[AlertSet.type])
//
//  println(x.map(_.castTo[UserId]))
//
//  // check json
//  println("-" * 100)
//
//  println(42.unsafeBox[BoxedInt].toJson.convertTo[BoxedInt])
//  println("set".toJson.convertTo[AlertState])
//  println(AlertSet.toJson)
//
//  // check unapply support
//  println("-" * 100)
//  val s = "set"
//
//  println(AlertState.unapply("other"))
//
//  s match {
//    case AlertState(AlertSet) => println("MATCH Set")
//    case AlertState(state) => println(s"MATCH other state: $state")
//    case s => println(s"Match Other string $s")
//  }
//
//  // check factory constraints
//  println("-" * 100)
//  println(Box.create[DeviceId](uuid))
//  println(Box.unsafe[DeviceId](uuid))
//  println(Box.value[DeviceId](Id.random[DeviceId]))

  //doesn't compile (as required)
//  println(Id.create[AlertState]("clear"))
//  println(Id.random[AlertState])
//  println(Id.value[AlertState]("clear".box[AlertState]))
 // println(Id.unsafe[AlertState]("clear"))

  // check caching, comment box.auto, safeid.auto
  println("-" * 100)

  println(Box.create[AlertState]("clear"))
  println(Box.create[AlertState]("set"))
  println(Box.create[AlertState]("set"))
  println(Box.create[DeviceId](UUID.randomUUID()))
  println(Id.random[DeviceId])

  "clear" match {
    case AlertState(AlertClear) => println("clear")
  }
  "set" match {
    case AlertState(AlertSet) => println("set")
  }

}
