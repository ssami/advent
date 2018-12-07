
val count = collection.mutable.Map[Int, Int]()
val oldnum = count.getOrElse(5, 0)
if (!count.contains(5)) count(5) = 20
val num = count.get(5)
val newnum = num.getOrElse(1)


import scala.collection.mutable.ArrayBuffer
import scala.util.Sorting
//import Day3.{overlapped, parseInput, square}
//
//import scala.io.Source
//
//def square(x: Int) =  x*x
//square(5)
//
//def frequency(lines: List[String]):Integer = {
//  val ints = lines.map(_.toInt)
//  return ints.sum
//}
//
//val source = Source.fromFile("/Users/sumi/gitrepos/advent/day1_input.txt")
//val lines = (for (line <- source.getLines()) yield line).toList
//
//frequency(lines)
//
//val sq = Array.ofDim[Boolean](1000, 1000)
//sq(563)(24) = true
//println(sq(563)(24))
//
//def parseInput(line: String):Claim = {
//  val pattern = """#(\d+) @ (\d+),(\d+): (\d+)x(\d+)"""".r
//  pattern.findAllIn(line).matchData foreach {
//    m => println(m.group(1))
//  }
////  val claim = Claim(splits.group(1).toInt,
////    splits.group(2).toInt,
////    splits.group(3).toInt,
////    splits.group(4).toInt)
////  return claim
//
//  return Claim(1, 2, 3, 4)
//}
//case class Claim(left: Int, top: Int, wid: Int, len: Int)
//val c = parseInput("#16 @ 646,318: 24x21")
//val line = "#16 @ 646,318: 24x21"
//val pattern = """#\d+ @ (\d+),(\d+): (\d+)x(\d+)""".r
//val pattern(left, top, wid, len) = line
//
//
//val claim = parseInput(line)
//var col = claim.left
//var row = claim.top
//
//for (i <- row to row + claim.len) {
//  for (j <- col to col + claim.wid) {
//
//  }
//}

//val pattern2 = "\\[(.*)\\] (.*)".r
//val line2 = "[1518-07-31 00:54] wakes up"
//val line3 = "[1518-04-09 00:01] Guard #3407 begins shift"
//val line4 = "[1518-01-01 00:01] Guard #3407 begins shift"
//val pattern2(date, info) = line2
//val dateFormat = "yyyy-MM-dd hh:mm"
//val dateObj = DateTimeFormatter.ofPattern(dateFormat).parse(date)
//val minute = dateObj.get(ChronoField.MINUTE_OF_HOUR)
//val linearr = Array(line2, line3, line4)
//val arr = ArrayBuffer[LocalDateTime]()
//linearr.foreach { line =>
//  val pattern2(date, info) = line
//  val df = DateTimeFormatter
//    .ofPattern(dateFormat)
//  val localTime = LocalDateTime.parse(line, df)
//  arr += localTime
//}
//println(arr.length)
//arr.sortBy(_.t)
//println(arr.mkString(" "))

def checkOppose(x: Char, y: Char): Boolean = {
  (x != y) && (x.toUpper == y || x.toLower == y)
}

checkOppose('a', 'A')
checkOppose('A', 'A')
checkOppose('A', 'a')
checkOppose('y', 'a')
checkOppose('Y', 'a')

