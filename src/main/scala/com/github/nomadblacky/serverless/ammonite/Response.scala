package com.github.nomadblacky.serverless.ammonite

import scala.beans.BeanProperty

case class Response(
    @BeanProperty isSuccess: Boolean,
    @BeanProperty message: String,
    @BeanProperty request: Request
)
