package safeid

import box.BoxFactory

trait IdFactory[I <: Id] extends BoxFactory[I] {

  def random: I
}
object IdFactory {
  def apply[I <: Id: IdFactory]: IdFactory[I] = implicitly[IdFactory[I]]
}
