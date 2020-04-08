package safeid

import box.Box

trait Id extends Box
object Id extends IdFunctions

trait IdOf[R] extends Id {
  type Repr = R
}
