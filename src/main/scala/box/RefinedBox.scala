package box

import eu.timepit.refined.api.Refined

trait RefinedBox extends Box {
  type T
  type P
  override type Repr = Refined[T,P]
}
object RefinedBox extends RefinedBoxFunctions

trait RefinedBoxOf[T0, P0] extends RefinedBox {
  override type T = T0
  override type P = P0
}
