package com.github.nomadblacky.serverless.ammonite

import scala.beans.BeanProperty

case class Response(@BeanProperty message: String, @BeanProperty request: Request)
