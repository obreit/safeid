package safeid.constructing

import safeid.Id

trait IdConstructor[T <: Id] {
  type UID = T#UID
  def create(uid: UID): T
  def value(id: T): UID
}

object IdConstructor {

  def apply[T <: Id](createId: T#UID => T, valueOfId: T => T#UID): IdConstructor[T] = new IdConstructor[T] {
    override def create(uid: UID): T = createId(uid)
    override def value(id: T): UID = valueOfId(id)
  }
}
