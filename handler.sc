import $file.LambdaUtil
import LambdaUtil._

import upickle.default._

case class Request(text: String, times: Int)
case class Response(text: String)

implicit val requestReader: Reader[Request] = macroR
implicit val responseWriter: Writer[Response] = macroW

object SimpleLambdaHandler extends AmmLambdaHandler[Request, Response] {
  def handle(request: Request): Response = {
    Response(request.text * request.times)
  }
}

@main def compile(): Unit = println("Compilation successful.")
@main def run(): Unit = SimpleLambdaHandler.run()
