import better.files.{File, _}
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging

object SettingsParser extends LazyLogging{
  private def getDefaultNumOfPages = ConfigFactory.load().getInt("num-of-pages")

  def areMetricsEnabled: Boolean = {
    ConfigFactory.load().getBoolean("metrics-enabled")
  }
  def getOutputFile: File = {
    val path: String = ConfigFactory.load().getString("output-path")
    path.toFile.createIfNotExists()
  }

  def getNumOfPages(args: Array[String]): Int = {
    def getDefaultNumWithLogMessage = {
      val defaultNumOfPages = SettingsParser.getDefaultNumOfPages
      logger.info(s"No number of pages to download provided, use default: $defaultNumOfPages")
      defaultNumOfPages
    }

    if (args.length == 0)
      return getDefaultNumWithLogMessage
    val IntRegEx = "(\\d+)".r
    args(0) match {
      case IntRegEx(num) => num.toInt
      case _ => getDefaultNumWithLogMessage
    }
  }
}