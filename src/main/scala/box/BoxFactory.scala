package box

trait BoxFactory[B <: Box] {

  def create(repr: B#Repr): Valid[B]
  //def empty: ID
  //def parse(str: String): ID
  //def random: ID

  def unsafe(repr: B#Repr): B

  def value(b: B): B#Repr
}
object BoxFactory {
  def apply[B <: Box: BoxFactory]: BoxFactory[B] = implicitly[BoxFactory[B]]
}
