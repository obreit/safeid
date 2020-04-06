package safeid

import box.BoxCstr
import source.IdSource

// see box.auto for explanation
package object auto {

  implicit def autoFact[I <: Id](implicit ev1: BoxCstr.Aux[I, I#Repr],
                                 ev2: IdSource[I#Repr]): IdFactory[I] = Id.factory[I]
}
