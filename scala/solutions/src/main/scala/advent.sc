import scala.io.Source

def square(x: Int) =  x*x
square(5)

def frequency(lines: List[String]):Integer = {
  val ints = lines.map(_.toInt)
  return ints.sum
}

val source = Source.fromFile("/Users/sumi/gitrepos/advent/day1_input.txt")
val lines = (for (line <- source.getLines()) yield line).toList

frequency(lines)