package box

import box.BoxCstr.Values
import shapeless.{Coproduct, Generic}

trait Box {
  type Repr

  def repr: Repr

  override final def equals(obj: scala.Any): Boolean = obj match {
    case that: Box if that.getClass == this.getClass => that.repr == this.repr
    case _ => false
  }

  override final def hashCode: Int = repr.hashCode

  override final def toString: String = repr.toString
}

object Box extends BoxFunctions {

  override type U = Box

  def factory[B <: U](implicit BC: BoxCstr.Aux[B, B#Repr]): BoxFactory[B] = new BoxFactory[B] {
    override def create(repr: B#Repr): Valid[B] = BC.create(repr)
    override def unsafe(repr: B#Repr): B = BC.unsafe(repr)
    override def value(b: B): B#Repr = b.repr
  }

  implicit class BoxOps[B <: Box](val b: B) extends AnyVal {

    def castTo[B2 <: Box: BoxFactory](implicit ev: B#Repr =:= B2#Repr): B2 =
      Box.unsafe[B2](b.repr)
  }
}

trait TypedBox[T] extends Box {
  override type Repr = T
}

/*
 Doing 'extends FactorySupport[B <: Box](BoxCstr[B])'
 or 'FactorySupport[B <: Box](implicit BC: BoxCstr.Aux[B, B#Repr])' gives weird compiler error
 but 'val x = BoxCstr[B]
     'extends FactorySupport[B <: Box](x)' works
*/
//abstract class FactorySupport[B <: Box](BC: BoxCstr.Aux[B, B#Repr]) {
//  implicit val f: BoxFactory[B] = Box.factory[B](BC)
//}

trait FactorySupport[B <: Box] {
  val f: BoxFactory[B]
}

trait UnapplySupport[B <: Box] {
  def values: Seq[B]

  def unapply(r: B#Repr): Option[B] = values.find(_.repr == r)

  def V[C <: Coproduct]()(implicit ev: Generic.Aux[B, C],
                          V: Values[B, C]): Seq[B] = V.values

  /*
  works without caching
  def unapplyNotCached[C <: Coproduct](r: B#Repr)(implicit ev: Generic.Aux[B, C],
                                                  V: Values[B, C]): Option[B] = {
    V.values.find(_.repr == r)
  }*/
}

