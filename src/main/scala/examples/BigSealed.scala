package examples

import box.{BoxOf, SealedFactorySupport}


sealed abstract class BigSealed(val repr: String) extends BoxOf[String]
object BigSealed extends SealedFactorySupport[BigSealed] {
  override val values = Values()

  case object A extends BigSealed("a")
  case object B extends BigSealed("b")
  case object C extends BigSealed("c")
  case object D extends BigSealed("d")
  case object E extends BigSealed("e")
  case object F extends BigSealed("f")
  case object G extends BigSealed("g")
  case object H extends BigSealed("h")
  case object I extends BigSealed("i")
  case object J extends BigSealed("j")
  case object K extends BigSealed("k")
  case object L extends BigSealed("l")
  case object M extends BigSealed("m")
  case object N extends BigSealed("n")
  case object O extends BigSealed("o")
  case object P extends BigSealed("p")
  case object Q extends BigSealed("q")
  case object R extends BigSealed("r")
  case object S extends BigSealed("s")
  case object T extends BigSealed("t")
  case object U extends BigSealed("u")
  case object V extends BigSealed("v")
  case object W extends BigSealed("w")
  case object X extends BigSealed("x")
  case object Y extends BigSealed("y")
  case object Z extends BigSealed("z")
  case object _1 extends BigSealed("1")
  case object _2 extends BigSealed("2")
  case object _3 extends BigSealed("3")
  case object _4 extends BigSealed("4")
  case object _5 extends BigSealed("5")
  case object _6 extends BigSealed("6")
  case object _7 extends BigSealed("7")
  case object _8 extends BigSealed("8")
  case object _9 extends BigSealed("9")
  case object _10 extends BigSealed("10")
  case object _11 extends BigSealed("11")
  case object _12 extends BigSealed("12")
  case object _13 extends BigSealed("13")
}