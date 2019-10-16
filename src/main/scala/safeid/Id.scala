package safeid

import safeid.constructing.IdConstructor
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
                       idConstr: IdConstructor[T]): IdFactory[T] = new IdFactory[T] {
    override def create(uid: T#UID): T = idConstr.create(uid)
    override def empty: T = idConstr.create(src.empty)
    override def parse(str: String): T = idConstr.create(src.parse(str))
    override def random: T = idConstr.create(src.random)
    override def value(id: T): T#UID = idConstr.value(id)
  }
}

trait TypedId[T] extends Id {
  override type UID = T
}
