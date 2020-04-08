package box

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

  implicit class BoxOps[B <: Box](val b: B) extends AnyVal {

    def castTo[B2 <: Box: BoxFactory](implicit ev: B#Repr =:= B2#Repr): B2 =
      Box.unsafe[B2](b.repr)
  }
}

trait TypedBox[T] extends Box {
  override type Repr = T
}


