package box

import eu.timepit.refined.api.Refined
import shapeless.{::, Generic, HNil}

trait RefinedBoxFunctions extends BoxFunctions {

  override type U = RefinedBox

  def to[B <: U](r: Refined[B#T, B#P])(implicit G: Generic.Aux[B, Refined[B#T, B#P] :: HNil]): B = G.from(r :: HNil)
}
