package safeid

import box.BoxFunctions

trait IdFunctions extends BoxFunctions {
  override type U = Id
  def random[I <: U: IdFactory]: I = IdFactory[I].random
}
