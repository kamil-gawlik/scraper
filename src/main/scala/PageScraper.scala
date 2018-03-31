import net.ruippeixotog.scalascraper.model._
import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import java.net.{InetSocketAddress, Proxy}

object BashOrgUrlBuilder {
  private val baseUrl = "http://bash.org.pl"
  private val latestPostsPath = "/latest/?page="

  private def getAllUrls(baseUrl: String, numOfPages: Int): List[String] = {
    List.range(1, numOfPages + 1) map { num: Int =>
      baseUrl + latestPostsPath + num
    }
  }

  def getNBashPages(num: Int) = getAllUrls(this.baseUrl, num)


}

case class Post(val id: Long, val points: Long, val content: String)

object PageScraper {

  val browser = new JsoupBrowser(proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.144.1.10", 8080)))

  def pageToPostsList(url: String) = {
    val page = browser.get(url)

    (page >> elementList(".post")) map { post =>
      println(postParser(post))
    }
  }

  def postParser(post: Element): Post = {
    val id = (post >> extractor(".qid", text)).replace("#", "").toLong
    val content: String = post >> extractor(".post-content", text)
    val points = post >> extractor(".points", text, asInt)
    Post(id, points, content)
  }
}

