
object Main {

  def main(args: Array[String]): Unit = {
    val urls = BashOrgUrlBuilder.getNBashPages(1)
    urls.map(url => PageScraper.pageToPostsList(url))
  }
}
