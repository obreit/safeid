package safeid

import box.Box

trait Id extends Any with Box
object Id extends IdFunctions

trait IdOf[R] extends Any with Id {
  type Repr = R
}
