package box

trait Box extends Any {
  type Repr

  def repr: Repr

  //TODO why need to remove final when working with anyval?
  override def equals(obj: scala.Any): Boolean = obj match {
    case that: Box if that.getClass == this.getClass => that.repr == this.repr
    case _ => false
  }

  override def hashCode: Int = repr.hashCode

  override final def toString: String = repr.toString
}

object Box extends BoxFunctions {

  override type U = Box

  implicit class BoxOps[B <: Box](val b: B) extends AnyVal {

    def castTo[B2 <: Box: BoxFactory](implicit ev: B#Repr =:= B2#Repr): B2 =
      Box.unsafe[B2](b.repr)
  }
}

trait BoxOf[R] extends Any with Box {
  override type Repr = R
}


