import Day3.{overlapped, parseInput, square}

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

val sq = Array.ofDim[Boolean](1000, 1000)
sq(563)(24) = true
println(sq(563)(24))

def parseInput(line: String):Claim = {
  val pattern = """#(\d+) @ (\d+),(\d+): (\d+)x(\d+)"""".r
  pattern.findAllIn(line).matchData foreach {
    m => println(m.group(1))
  }
//  val claim = Claim(splits.group(1).toInt,
//    splits.group(2).toInt,
//    splits.group(3).toInt,
//    splits.group(4).toInt)
//  return claim

  return Claim(1, 2, 3, 4)
}
case class Claim(left: Int, top: Int, wid: Int, len: Int)
val c = parseInput("#16 @ 646,318: 24x21")
val line = "#16 @ 646,318: 24x21"
val pattern = """#\d+ @ (\d+),(\d+): (\d+)x(\d+)""".r
val pattern(left, top, wid, len) = line


val claim = parseInput(line)
var col = claim.left
var row = claim.top

for (i <- row to row + claim.len) {
  for (j <- col to col + claim.wid) {

  }
}