import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object Advent7 extends App {
  /**
    *
    * After a lot of struggle, looked up the subreddit and saw kahn's algo:
    * https://en.wikipedia.org/wiki/Topological_sorting#Kahn's_algorithm
    * I was basically also finding the starting root nodes and using a priority queue
    * with alphabetical sort, but this algo says to *remove the edge* once it's been visited.
    * if all the edges have been removed, then add the node to the list.
    * We can use the dependencies list to store the edges.
    *
    *
    */


  val lines = Utils.readFile("/Users/sumi/gitrepos/advent/day7_input.txt")

  val roots = mutable.Map[String, Step]()   // map of all steps w/o dependencies
  val allSteps = processLine(lines)         // tracks all the steps, as a Map of step name to Step object

  val toProcess = new mutable.PriorityQueue[Step]()   // the priority queue we start from
  val orderList = new ArrayBuffer[Step]()   // stores the finally sorted list


  def processLine(lines: List[String]): mutable.Map[String, Step] = {
    val allSteps = mutable.Map[String, Step]() // tracks step name to Step object
    lines.foreach( line => {
        val pattern = """Step (\w) must be finished before step (\w) can begin.""".r
        val pattern(s1, s2) = line
        if (!allSteps.contains(s1)) {
          val st = Step(s1, new ArrayBuffer[String], new ArrayBuffer[String])
          allSteps(s1) = st
          roots(s1) = st      // add s1 to the roots list, we'll decide later if it's a proper
          st.next += s2 // add s2 to next node list
        }
        else {
          val s = allSteps(s1)
          s.next += s2
        }
        if (!allSteps.contains(s2)) {
          val st = Step(s2, new ArrayBuffer[String], new ArrayBuffer[String])
          allSteps(s2) = st
          st.dependencies += s1 //add s1 to dependency list
        }
        else {
          val s = allSteps(s2)
          s.dependencies += s1
        }

      // i don't like modifying roots from within this method. it's basically a side effect
        if (roots.contains(s2)) {
          // s2 has a dependency, so it's not actually a root
          // so remove it
          roots -= s2
        }
      }
    )
    return allSteps
  }

  // put all the roots into the sorted priority queue
  roots.keysIterator.foreach(key => toProcess.enqueue(roots(key)))

  // now we can do the real work
  while (toProcess.nonEmpty) {
    val step = toProcess.dequeue()
    // process it - add to list
    orderList += step
    step.next.foreach(node => {
      // remove edge from this node - that is,
      // remove the dep of step from the node's dependency list
      val neighborStep = allSteps(node)
      neighborStep.dependencies -= step.name
      // if this step has no more dependencies, it can be the next one to process...
      //.. as long as it's alphabetically in order
      if (neighborStep.dependencies.isEmpty) toProcess.enqueue(neighborStep)
    })

  }

  println()
  orderList.foreach(step => print(step.name))
  println()






  case class Step(name: String, next: ArrayBuffer[String], dependencies: ArrayBuffer[String]) extends Ordered[Step] {
    override def compare(that: Step): Int = {
      that.name compare this.name
    }
  }

}
