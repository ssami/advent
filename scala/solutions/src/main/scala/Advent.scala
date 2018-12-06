import java.util.Date

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


object Day4 extends App {

  val lines = Utils.readFile("/Users/sumi/gitrepos/advent/day4_input.txt")

//  def parseInput(line: String): Record = {
//    val pattern = "[1518-07-31 00:54] (.*)\n".r
//  }

}

case class Guard(id: String, asleep: Boolean)
case class Record(date: Date, guard: Guard)



object Day5 extends App {
  // create new string as you go along char by char
  // when you look ahead one and you have to remove,
  // go to the next char
  // check if new string has an opposing
  // remove from new string and increment i in old string

  // this should really be done through recursion
  /**
    * pass latest index of new string and index of old string
    * if indices oppose each other then remove from new string,
    * increment index of old string
    * if not, then add the current char to new string and
    * increase index of old string
    * don't bother looking ahead!!
    */

  val line = Utils.readFile("/Users/sumi/gitrepos/advent/day5_input.txt")(0)
  val lineChars = line.toList
  val newString = new StringBuilder
  newString += lineChars(0)

  var p = 1
  while (p < line.length) {
    val prevIndex = newString.length - 1
    if (prevIndex < 0) {
      newString.append(lineChars(p))
      p += 1
    }
    else {
      val prev = newString.charAt(prevIndex)
      if (checkOppose(prev, lineChars(p))) {
        // if the chars oppose each other, remove the existing char and move to next index
        newString.deleteCharAt(prevIndex)
        p += 1
      }
      else {
        newString.append(lineChars(p))
        p += 1
      }
    }
  }

  println(newString.size)

  def checkOppose(x: Char, y: Char): Boolean = {
    (x != y) && (x.toUpper == y || x.toLower == y)
  }

}