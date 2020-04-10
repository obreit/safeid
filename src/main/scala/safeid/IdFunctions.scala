package safeid

import box.{BoxFactory, BoxFunctions}
import source.IdSource

trait IdFunctions extends BoxFunctions {
  override type U = Id
  def random[I <: U: BoxFactory](implicit IS: IdSource[I#Repr]): I = unsafe(IS.random)
}
