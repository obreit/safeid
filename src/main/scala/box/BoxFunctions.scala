package box

trait BoxFunctions {
  // to restrict the creation of subtypes and disallow "unintuitive" instantiations
  // e.g. Id.create[NotAnId](r) would be possible --- it's not wrong, but a bit unintuitive if we try to model
  // an 'Id' but use the factory methods to create sth that's not an 'Id'
  type U <: Box

  def create[B <: U: BoxFactory](r: B#Repr): Valid[B] = BoxFactory[B].create(r)
  def unsafe[B <: U: BoxFactory](r: B#Repr): B = BoxFactory[B].unsafe(r)
  def value[B <: U: BoxFactory](b: B): B#Repr = BoxFactory[B].value(b)
}
