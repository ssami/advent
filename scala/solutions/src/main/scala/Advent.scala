import scala.io.Source

object Day1 extends App {
  def frequency(lines: List[String]):Integer = {
    val ints = lines.map(_.toInt)
    return ints.sum
  }

  val source = Source.fromFile("/Users/sumi/gitrepos/advent/day1_input.txt")
  val lines = (for (line <- source.getLines()) yield line).toList

  println(frequency(lines))
}
