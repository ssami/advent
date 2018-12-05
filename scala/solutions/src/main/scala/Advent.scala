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


object Day3 extends App {
  val lines = Utils.readFile("/Users/sumi/gitrepos/advent/day3_input.txt")
  val square = Array.ofDim[Boolean](2000, 2000)
  val olSq = scala.collection.mutable.Set[Square]()


  for (l <- lines) {
    val claim = parseInput(l)
    var col = claim.left
    var row = claim.top
    for (i <- row until row + claim.len) {
      for (j <- col until col + claim.wid) {
        if (square(i)(j)) {
          val s = Square(i, j)
          if (!olSq(s)) olSq += s
        }
        else square(i)(j) = true
      }
    }
  }


  println(olSq.size)

  def parseInput(line: String):Claim = {
    val pattern = """#\d+ @ (\d+),(\d+): (\d+)x(\d+)""".r
    val pattern(left, top, wid, len) = line
    val claim = Claim(left.toInt, top.toInt, wid.toInt, len.toInt)
    claim
  }

}

case class Claim(left: Int, top: Int, wid: Int, len: Int)
case class Square(row: Int, col: Int)