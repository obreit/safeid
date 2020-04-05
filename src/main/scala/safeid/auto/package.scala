package safeid

import box.{Box, BoxCstr}
import source.IdSource

package object auto {

  implicit def autoFact[I <: Id](implicit c: BoxCstr.Aux[I, I#Repr],
                                 src: IdSource[I#Repr]): IdFactory[I] = Id.factory[I]
}
