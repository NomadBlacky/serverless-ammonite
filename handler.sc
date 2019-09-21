import $ivy.`org.jsoup:jsoup:1.12.1`

import org.jsoup._

@main def compile(): Unit = println("Compilation successful.")

@main def run(): Unit = {
  val doc = Jsoup.connect("https://www.google.com").get
  val title = doc.getElementsByTag("title").first().text()
  println(title)
}
