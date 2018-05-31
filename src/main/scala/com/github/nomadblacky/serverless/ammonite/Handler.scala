package com.github.nomadblacky.serverless.ammonite

import ammonite.util.Res._
import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}

class Handler extends RequestHandler[Request, Response] {

  def handleRequest(input: Request, context: Context): Response = {
    val (res, _) = ammonite
      .Main(
        // Configuration REPL
      )
      .runCode(
        """println("Hello Ammonite!")"""
      )

    val message = res match {
      case Success(_) => "OK"
      case f: Failing =>
        f match {
          case Failure(msg)      => msg
          case Exception(t, msg) => s"""$msg\n${t.getStackTrace.mkString("\n")}"""
          case Skip              => "Skipped"
          case Exit(value)       => s"Exit: $value"
        }
    }

    Response(res.isSuccess, message, input)
  }
}
