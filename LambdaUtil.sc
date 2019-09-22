import upickle.default._

trait AmmLambdaHandler[Request, Response] {
  def run()(implicit reader: Reader[Request], writer: Writer[Response]): Unit = {
    // https://github.com/todokr/scala-on-lambda-snappy/blob/1fd9507092fe025199c8d8c79613e0835465ea68/graalvm/hello/src/main/scala/Hello.scala
    val runtime = System.getenv("AWS_LAMBDA_RUNTIME_API")

    while (true) {
      val rawRequest = requests.get(s"http://$runtime/2018-06-01/runtime/invocation/next")
      val requestId  = rawRequest.headers("lambda-runtime-aws-request-id").head

      try {
        val request = read[Request](rawRequest.text())
        val response = handle(request)
        val rawResponse = write(response)

        requests.post(
          url = s"http://$runtime/2018-06-01/runtime/invocation/$requestId/response",
          data = rawResponse
        )
      } catch {
        case e: Exception =>
          e.printStackTrace()
          requests.post(
            url = s"http://$runtime/2018-06-01/runtime/invocation/$requestId/error",
            data = ujson.Obj("error" -> e.getMessage).render()
          )
      }
    }
  }
  def handle(request: Request): Response
}
