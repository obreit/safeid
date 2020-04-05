package safeid

import box.{Box, BoxCstr, Valid}
import source.IdSource

trait Id extends Box
object Id extends IdFunctions {
  def factory[I <: U](implicit BC: BoxCstr.Aux[I, I#Repr],
                      IS: IdSource[I#Repr]): IdFactory[I] = new IdFactory[I] {
    override def create(repr: I#Repr): Valid[I] = BC.create(repr)
    override def unsafe(repr: I#Repr): I = BC.unsafe(repr)
    override def value(b: I): I#Repr = b.repr
    override def random: I = BC.unsafe(IS.random)
  }
}

trait TypedId[T] extends Id {
  type Repr = T
}
