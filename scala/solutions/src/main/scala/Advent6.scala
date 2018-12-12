import Day6.dim

import scala.collection.immutable.{ListMap, SortedMap}
import scala.collection.mutable.ArrayBuffer


/* 1. how do you determine if an area is infinite?
* maybe if it's finite you can see it being bounded by other coordinates
*
* 2. how do you determine which grid square is closest to a coordinate?
* take each [x,y] point in the  matrix and calculate distance to each coordinate
* (there are only 50 thank god)
*
* 3. how do you mark the grid square? can put a Point in each grid or a null
* if it's equivalent distance from others
*/
object Day6 extends App {
  val lines = Utils.readFile("/Users/sumi/gitrepos/advent/day6_input.txt")
  val pattern = """(\d+), (\d+)""".r
  val coords = ArrayBuffer[Point]()
  lines.foreach { line =>
    val pattern(x, y) = line
    coords += Point(x.toInt, y.toInt)
  }

  // first discover all the points
  val dim = 400
  val plane = Array.ofDim[Point](dim, dim)
  for (i <- 0 until dim) {
    for (j <- 0 until dim) {
      var min = Integer.MAX_VALUE // track min dist from each point
      coords.foreach { c =>
        val pointDist = manhattan(i, j, c)
        //  update with closest point
        // or if the grid is the point anyway
        if (pointDist < min || pointDist == 0) {
          plane(i)(j) = c
          min = pointDist
        } else if (pointDist == min) {
          plane(i)(j) = null // not a great idea to use null, tbd
        }
      }
    }
  }

//  printOut(plane)

  // create map to store bounded and count
  val count = collection.mutable.Map[Point, Int]()
  val edge = collection.mutable.Set[Point]()

  // now go through the plane to find the bounded, non finite points
  for (i <- 0 until dim) {
    for (j <- 0 until dim) {
      val curPt = plane(i)(j)
      // if we find a point in an edge space, immediately disqualify
      if (isEdge(i, j)) {
        edge += curPt
      }
      // check if any other squares are of the same point type
      // if not, add to hashmap
      if (!edge.contains(curPt)) {
        val ct = count.getOrElse(curPt, 0)
        count(curPt) =  ct + 1
      }
    }
  }

  // ideally i would have inserted the points into a
  // heap with a comparator of some kind
  // sorting from high to low: https://alvinalexander.com/scala/how-to-sort-map-in-scala-key-value-sortby-sortwith
  println(ListMap(
    count.toSeq.sortWith(_._2 > _._2):_*))



  def printOut(plane: Array[Array[Point]]) = {
    plane.foreach { arr =>
      arr.foreach { p =>
        if (null == p) print("X")
        else {
          print(p.x + ":" + p.y + ",")
        }
      }
      println()
    }
  }

  /**
    * Returns true if bound by another point in all 4 directions
    *
    * @param i
    * @param j
    * @return
    */
  def isEdge(i: Int, j: Int): Boolean = {
    // check if we are at the edges. any point there is unbounded
    return (i == 0 || j == 0 || i == dim - 1 || j == dim - 1)
  }


  /**
    * dist betw p1,p2 and q1, q2 is |p1-q1| + |p2-q2|
    *
    * @param i
    * @param j
    * @param p
    * @return
    */
  def manhattan(x: Int, y: Int, p: Point): Int = {
    return Math.abs(x - p.x) + Math.abs(y - p.y)
  }
}

// this denotes the coordinate closest to the grid square
case class Point(x: Int, y: Int)
