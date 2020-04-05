import spray.json._

package object json {

  def jsonFmt[T](readFun: JsValue => T)(writeFun: T => JsValue): JsonFormat[T] = new JsonFormat[T] {
    override def write(obj: T): JsValue = writeFun(obj)
    override def read(json: JsValue): T = readFun(json)
  }
}
