package source

import java.util.UUID

import scala.util.Random

trait IdSource[UID] {

  def random: UID

  def parse(str: String): UID

  def empty: UID
}

object IdSource {

  def apply[UID](randomFun: () => UID,
                 parseStr: String => UID,
                 emptyVal: UID): IdSource[UID] = new IdSource[UID] {
    override def random: UID = randomFun()
    override def parse(str: String): UID = parseStr(str)
    override def empty: UID = emptyVal
  }

  implicit val uuidSource: IdSource[UUID] = IdSource(
    UUID.randomUUID,
    UUID.fromString,
    UUID.fromString("00000000-0000-0000-0000-000000000000")
  )

  implicit val intSource: IdSource[Int] = IdSource(Random.nextInt, _.toInt, 0)

  implicit val longSource: IdSource[Long] = IdSource(Random.nextLong, _.toLong, 0L)

  implicit val bigIntSource: IdSource[BigInt] = IdSource(() => BigInt(16, Random), BigInt.apply, BigInt(0))

  implicit val stringSource: IdSource[String] = IdSource(
    () => Random.alphanumeric.take(32).mkString, identity, ""
  )
}
