package safeid

import shapeless.{Generic, HNil, ::}
import source.IdSource

trait Id {

  // Underlying id, not UUID :) (i always read it as UUID, so confusing)
  type UID

  def underlying: UID

  override final def equals(obj: scala.Any): Boolean = obj match {
    case that: Id if that.getClass == this.getClass => that.underlying == this.underlying
    case _ => false
  }

  override final def hashCode: Int = underlying.hashCode

  override final def toString: String = underlying.toString
}

object Id extends IdFunctions {

  def factory[T <: Id](implicit src: IdSource[T#UID],
                       g: Generic.Aux[T, T#UID :: HNil]): IdFactory[T] = new IdFactory[T] {
    override def create(uid: T#UID): T = g.from(uid :: HNil)
    override def empty: T = g.from(src.empty :: HNil)
    override def parse(str: String): T = g.from(src.parse(str) :: HNil)
    override def random: T = g.from(src.random :: HNil)
    override def value(id: T): T#UID = g.to(id).head
  }
}

trait TypedId[T] extends Id {
  override type UID = T
}
