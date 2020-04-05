package box.syntax

import box.{Box, BoxFactory, Valid}

object BoxImplicits {

  implicit class BoxCstrOps[R](val r: R) extends AnyVal {
    def box[B <: Box: BoxFactory](implicit ev: R =:= B#Repr): Valid[B] =
      Box.create[B](r)

    def unsafeBox[B <: Box: BoxFactory](implicit ev: R =:= B#Repr): B =
      Box.unsafe[B](r)
  }
}
