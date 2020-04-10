package box.syntax

import box.{Box, BoxFactory, RefinedBox, Valid}
import eu.timepit.refined.api.Refined
import shapeless.{Generic, HNil, ::}

object BoxImplicits {

  implicit class BoxCstrOps[R](val r: R) extends AnyVal {
    def box[B <: Box: BoxFactory](implicit ev: R =:= B#Repr): Valid[B] =
      Box.create[B](r)

    def unsafeBox[B <: Box: BoxFactory](implicit ev: R =:= B#Repr): B =
      Box.unsafe[B](r)

    def toRef[B <: RefinedBox](implicit ev: R =:= Refined[B#T, B#P],
                               ev2: Generic.Aux[B, Refined[B#T, B#P] :: HNil]): B =
      RefinedBox.to[B](r)
  }
}
