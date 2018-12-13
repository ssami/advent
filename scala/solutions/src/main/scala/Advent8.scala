import scala.collection.mutable.ArrayBuffer

object Advent8 extends App {


  val line = Utils.readFile("/Users/sumi/gitrepos/advent/day8_input.txt")
  val nums = line(0).split(" ").map(_.toInt).toList // list of nums
  val root = buildTree(nums, 0)
  val sum = countMetadata(root)
  println(sum)

  // y no tail recurse :( :( :(

  def buildTree(nums: List[Int], nodeI: Int): Node = {
    // nodeI points to num of children
    val childrenSize = nums(nodeI)
    val mdSize = nums(nodeI+1)
    if (nums(nodeI) == 0) {
      // if the number of children is 0 we hit a leaf
      // metadata info starts 2 away from current index
      val metadataInfoIndex = nodeI+2
      val node = Node(childrenSize, mdSize,
        metadataInfoIndex,
        nums.slice(metadataInfoIndex, metadataInfoIndex+mdSize).toArray,
        null)
      // once we return this node, parent must collect it and put into children arraybuffer
      return node
    }
    else {
      // this node has children
      // until i have finished all my children
      val children = new ArrayBuffer[Node]()
      var nextIndex = nodeI + 2 // by default start after numChild, numMeta
      for (i <- 0 until childrenSize) {
        val child = buildTree(nums, nextIndex)    // really not tail recursive
        nextIndex = child.metadataIndex + child.metadataSize
        children += child
      }
      val metadata = nums.slice(nextIndex, nextIndex+mdSize).toArray
      return Node(childrenSize, mdSize, nextIndex, metadata, children)
    }
  }

  def countMetadata(curr: Node): Int = {
    /// DFS to find sum
    if (null == curr.children || curr.children.isEmpty) curr.metadata.sum
    // map each child into a sum using this recursive function and then add all those results
    else curr.metadata.sum + curr.children.map(countMetadata).sum
  }

  // ArrayBuffer because we need to add children
  case class Node(numChildren: Int,
                  metadataSize: Int,
                  metadataIndex: Int,
                  metadata: Array[Int],
                  children: ArrayBuffer[Node])

}
