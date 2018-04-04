import java.net.{InetSocketAddress, Proxy}

import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._


object PageScraper extends Instrumented {
  private val browser = new JsoupBrowser(proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.144.1.10", 8080)))
  private val baseUrl = "http://bash.org.pl"
  private val latestPostsPath = "/latest/?page="

  private def getAllUrls(baseUrl: String, numOfPages: Int): List[String] = {
    List.range(1, numOfPages + 1) map { num: Int =>
      baseUrl + latestPostsPath + num
    }
  }

  private def getNBashPages(num: Int): List[String] = getAllUrls(this.baseUrl, num)

  private[this] val pageLoadingMetric = metrics.timer("page loading")
  private[this] val postsCounter = metrics.counter("all posts counter")

  def pageToPostsList(url: String): List[Post] = pageLoadingMetric.time {
    val page = browser.get(url)
    (page >> elementList(".post")) map { post =>
      postsCounter += 1
      Post.htmlToPost(post)
    }
  }

  private[this] val allPostsLoadingMetric = metrics.timer("all post loading")

  def getPostsFromNPages(num: Int): List[Post] = allPostsLoadingMetric.time {
    getNBashPages(num) flatMap { url => pageToPostsList(url) }
  }
}




