import java.util

import better.files._
import com.codahale.metrics.{ConsoleReporter, MetricRegistry}
import com.typesafe.scalalogging.LazyLogging
import nl.grons.metrics4.scala.Counter


trait Instrumented extends nl.grons.metrics4.scala.InstrumentedBuilder {
  val metricRegistry = Main.metricRegistry
}


object Main extends LazyLogging {
  val metricRegistry = new com.codahale.metrics.MetricRegistry()

  def main(args: Array[String]): Unit = {
    val numOfPages = SettingsParser.getNumOfPages(args)
    val posts = PageScraper.getPostsFromNPages(numOfPages)
    val outputFile: File = SettingsParser.getOutputFile

    outputFile appendLines Post.toJson(posts)

    if(SettingsParser.areMetricsEnabled)
      ConsoleReporter.forRegistry(metricRegistry).build().report()
  }

}
