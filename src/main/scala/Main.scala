import safeid.Id
import safeid.examples.{DeviceId, UserId}

object Main extends App {

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
}
