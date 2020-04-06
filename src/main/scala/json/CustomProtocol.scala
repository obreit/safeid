package json

import spray.json.DefaultJsonProtocol

trait CustomProtocol extends DefaultJsonProtocol with UUIDFmt with BoxFmt
object CustomProtocol extends CustomProtocol
