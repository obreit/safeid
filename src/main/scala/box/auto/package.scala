package box

// Automatically generate a BoxFactory
// Use with care! nothing is cached, this is re-run for every call that requires a factory
// If imported, this would also take precedence over cached factories in a classes companion!
package object auto {
  implicit def autoFactory[B <: Box](implicit ev: BoxCstr.Aux[B, B#Repr]): BoxFactory[B] = Box.factory[B]
}
