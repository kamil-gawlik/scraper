import net.ruippeixotog.scalascraper.dsl.DSL.extractor
import net.ruippeixotog.scalascraper.model.Element
import net.ruippeixotog.scalascraper.scraper.ContentExtractors.text
import org.json4s.NoTypeHints
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.write
import net.ruippeixotog.scalascraper.dsl.DSL._


case class Post(val id: Long, val points: Long, val content: String)

object Post {
  implicit val formats = Serialization.formats(NoTypeHints)

  def toJson(post: Post): String = {
    write(post)
  }

  def toJson(posts: List[Post]): String = {
    posts map { p => this.toJson(p) } reduce (_ + "\n" + _)
  }


  def htmlToPost(post: Element): Post = {
    val id: Long = (post >> extractor(".qid", text)).replace("#", "").toLong
    val points: Long = (post >> extractor(".points", text)).toLong
    val content: String = post >> extractor(".post-content", text)
    Post(id, points, content)
  }
}
