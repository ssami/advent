import java.io.{BufferedWriter, File, FileWriter}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object Advent10 extends App {

  /**
    * --- putting on hold until i can figure out scaling
    *
    *
    * 1. Trickiest will be how to represent the negative vectors on a 2D matrix.
    * If you have a NxN matrix (oddnumber N) then the middle square, which is (n/2+1, n/2+1) will be coordinate 0
    *
    * 2. Then for each of the inputs, (n/2+1, n/2+1) - (x, y).
    * E.g. for a 11x11 matrix, (6,6) is the base coordinate. If (x,y) = (3, -2) then the resulting matrix grid point will be (3, 4)
    *
    * 3. Now, every "tick", update the matrix with the velocity.
    * Oops... will velocity be affected? Not if the velocity is just the coordinate change.
    *
    * 4. We forgot to think about clashes: what if one light point moves into the space of another?
    * Ideally they would all move at the same instant.
    *
    * 4. Keep a map of Point + Velocity -> ArrayBuffer of where the point is at each instant.
    * Every 'tick', look up the appropriate index in the ArrayBuffer and rebuild the map
    *
    * Whoops... every point is huge in terms of sheer numbers. Need to build a huge matrix,
    * or scale them down.
    *
    * Java heap error constantly. But if we scale down the sizes we may squish the final message.
    * Difference between highest and lowest index is: 104272
    */

  val lines = Utils.readFile("/Users/sumi/gitrepos/advent/day10_input.txt")
  val pointMap = mutable.Map[PointVelocity, ArrayBuffer[PointVelocity]]()   // how to collect all the parsed input into an immutable map??
  var lowest = Integer.MAX_VALUE // init - prob a better way of finding highest/lowest in all these numbers
  var highest = Integer.MIN_VALUE // init

  val file = new File("/Users/sumi/gitrepos/advent/day10_output.txt")
  val bw = new BufferedWriter(new FileWriter(file))
  bw.write("test")
  bw.flush()

  lines.foreach( line => {
    val p = parseLine(line)
    pointMap(p) = new ArrayBuffer[PointVelocity]()
    // init array with data
    pointMap(p) += PointVelocity(p.x + p.v1, p.y + p.v2, p.v1, p.v2)
    highest = getHighest(p)
    lowest = getLowest(p)
  })


  val scalingFactor = 50
  val dim = getDim(highest, lowest, scalingFactor, 500) // buffer accounts for movements of points
  // find the "central" point so that we won't have to use negative indices
  val centralPointIndex = (dim/2)+1

  val ticks = 10  // number of seconds
  for (i <- 0 until ticks){
    println("Now processing tick: " + i)
    pointMap.keysIterator.foreach(k => {
      // use the last known position to update to new position
      val lastPos = pointMap(k).last
      val newP = PointVelocity(lastPos.x + lastPos.v1, lastPos.y + lastPos.v2, lastPos.v1, lastPos.v2)
      pointMap(k) += newP
    })
    printSpace(pointMap, dim, i)
  }
  bw.close()


  def printSpace(pointMap: mutable.Map[PointVelocity, ArrayBuffer[PointVelocity]],
                 dim: Int, index: Int): Unit = {
    val map = Array.fill[Int](dim, dim)(0)
    pointMap.keysIterator.foreach(k => {
      val pv = pointMap(k)(index)
      // normalize the indices based on central point
      val col = centralPointIndex+(pv.x/scalingFactor)
      val row = centralPointIndex+(pv.y/scalingFactor)
      map(row)(col) = 1
    })
    printMap(map)
    Thread.sleep(10000)    // simulate a tick
  }

  def printMap(map: Array[Array[Int]]): Unit = {
    map.foreach(row => {
      row.foreach(x => print(x.toString))
      println()
    })
//    bw.flush()  // write out
  }

  def getDim(i: Int, j: Int, scale: Int, buffer: Int): Int = {
    val span = (i/scale) - (j/scale)
    if (span % 2 == 0) return span + buffer   // return odd number with some buffer
    else return span + buffer - 1
  }

  def parseLine(s: String): PointVelocity = {
    val pattern = """position=<([-\s]\d+),\s?([-\s]\d+)> velocity=<([-\s]\d), ([-\s]\d)>""".r
    val pattern(x,y,v1,v2) = s
    val pv = PointVelocity(x.trim().toInt, y.trim().toInt,
      v1.trim().toInt, v2.trim().toInt)
    return pv
  }

  def getHighest(pointVelocity: PointVelocity): Int = {
    if (pointVelocity.x > highest) return pointVelocity.x
    else if (pointVelocity.y > highest) return pointVelocity.y
    return highest
  }

  def getLowest(pointVelocity: PointVelocity): Int = {
    if (pointVelocity.x < lowest) return pointVelocity.x
    else if (pointVelocity.y < lowest) return pointVelocity.y
    return lowest
  }

  case class PointVelocity(x: Int, y: Int, v1: Int, v2: Int)

}
