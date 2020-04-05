package json

import spray.json.DefaultJsonProtocol

trait CustomProtocol extends DefaultJsonProtocol with BoxFmt
object CustomProtocol extends CustomProtocol
