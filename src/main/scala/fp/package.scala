package object fp {

  implicit class Pipe[T](val t: T) extends AnyVal {
    def |>[U](f: T => U): U = f(t)
  }
}
