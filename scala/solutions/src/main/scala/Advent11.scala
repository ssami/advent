import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object Advent11 extends App {

  val gridId = 9110

  // need to update the map with point values
  val grid = ArrayBuffer.fill(300,300)(0) // fill with 0's to start
  for (i <- 0 until 300) {
    for (j <- 0 until 300) {
      grid(i)(j) = calcPower(Point(i+1, j+1))
    }
  }
  var maxPower = Integer.MIN_VALUE
  val maxP = gridCalc()
  val x = maxP.c + 1
  val y = maxP.r + 1
  println(x, y)


  def gridCalc(): Point = {
    // increase by 1 in each direction so that you get all the 'windows' of 3x3 squares
    var r = 0
    var c = 0
    for (i <- 0 until 297) {
      for (j <- 0 until 297) {
        val tp = totalPower(i, j)
        if (tp > maxPower){
          maxPower = tp
          r = i
          c = j
        }
      }
    }
    return Point(r, c)
  }

  def totalPower(r: Int, c: Int): Int = {
    var power = 0
    for (i <- r until r+3) {
      for (j <- c until c+3) {
        power += grid(i)(j)
      }
    }

    return power
  }


  def calcPower(point: Point): Int = {
    val x = point.c
    val y = point.r
    val rackId = x + 10
    val power = calcHundredsDigit(((rackId * y) + gridId) * rackId) - 5

    return power
  }

  def calcHundredsDigit(input: Int): Int = {
//    val hun = 10000
//    var counter = 10
//    var x = input
//    var digit = 0
//    while (counter < hun && x > 0) {
//      // take the mod value if avail
//      if (x < 10) {
//        digit = x
//      } else {
//        digit = x % 10
//        x /= 10   // x  divide by 10 to get rid of the last digit
//      }
//      counter *= 10     // the next digit place
//    }
//
//    if (x > 0) digit else x

    // or you can do the lazy thing and just select the char

    val chars = input.toString.split("")
    if (chars.size > 2) {
      val index = chars.size - 3
      return chars(index).toInt
    } else {
      return 0
    }

  }

  case class Point(r: Int, c: Int)

}
