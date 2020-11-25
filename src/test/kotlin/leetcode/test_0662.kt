@file:Suppress("PackageDirectoryMismatch")

package leetcode.test_0662

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import leetcode.util.TreeNode
import leetcode.util.deserialize
import leetcode.util.loadTestJson
import java.util.*
import kotlin.math.max
import kotlin.test.Test
import kotlin.test.assertEquals


data class NodeAndIndex(val node: TreeNode, val index: Int)

// O(n) time. O(n) space. BFS.
class Solution {
    fun widthOfBinaryTree(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }

        var result = 1
        val queue = ArrayDeque<NodeAndIndex>()
        queue.addLast(NodeAndIndex(root, 0))

        while (queue.isNotEmpty()) {
            result = max(result, queue.last.index - queue.first.index + 1)
            val prevLevelSize = queue.size

            for (i in 0 until prevLevelSize) {
                val curr = queue.removeFirst()
                val left = curr.node.left
                val right = curr.node.right
                if (left != null) {
                    queue.addLast(NodeAndIndex(left, curr.index shl 1))
                }
                if (right != null) {
                    queue.addLast(NodeAndIndex(right, (curr.index shl 1) + 1))
                }
            }
        }

        return result
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val msg = Json.encodeToString(Args.serializer(), case.args)
            val root = case.args.root.deserialize()
            val actual = Solution().widthOfBinaryTree(root)
            assertEquals(case.expected, actual, msg)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: Int)

    @Serializable
    class Args(val root: List<Int?>)
}
