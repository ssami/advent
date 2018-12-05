import scala.io.Source

object Utils {

  def readFile(fileName: String):List[String] = {
    val source = Source.fromFile(fileName)
    val lines = (for (line <- source.getLines()) yield line).toList
    return lines
  }
}
