import shapeless.{Generic, HNil}

package object box {

  type GenericForObject[O] = Generic.Aux[O, HNil]
  type Valid[T] = Either[String, T]
}
