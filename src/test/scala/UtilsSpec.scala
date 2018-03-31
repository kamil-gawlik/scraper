import org.scalatest._

class UtilsSpec extends FlatSpec {

  var bashUrlRegex: String = "(http:\\/\\/)?bash.org.pl\\/latest\\/\\?page=[0-9]+"

  "BashOrgUrlBuilder" should "generate n sequential urls" in {
    val numOfUrls = 5
    val res: List[String] = BashOrgUrlBuilder.getNBashPages(numOfUrls)
    assert(res.length == numOfUrls)
    assert(res.head matches bashUrlRegex)
  }
}
