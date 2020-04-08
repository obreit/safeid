package safeid

import box.Box

trait Id extends Box
object Id extends IdFunctions

trait TypedId[T] extends Id {
  type Repr = T
}
