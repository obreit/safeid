package box

import shapeless.{Coproduct, Generic}

// Support for sealed hierarchies extending 'Box'
trait SealedFactorySupport[B <: Box] {

  def values: Map[B#Repr, B]

  private lazy val valuesStr: String = values.values.mkString(",")

  implicit val f: BoxFactory[B] = BoxFactory.instance[B] { repr =>
    values.get(repr).toRight(s"Unsupported value: '$repr', supported: '$valuesStr")
  }

  def unapply(r: B#Repr): Option[B] = values.get(r)

  def Values[C <: Coproduct]()(implicit ev: Generic.Aux[B, C],
                               V: Values[B, C]): Map[B#Repr, B] = V.values
}
