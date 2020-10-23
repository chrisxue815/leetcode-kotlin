package leetcode.test_0669

import kotlinx.serialization.Serializable
import leetcode.util.TreeNode
import leetcode.util.deserialize
import leetcode.util.loadTestJson
import leetcode.util.serialize
import kotlin.test.Test
import kotlin.test.assertEquals

// O(n) time. O(log(n)) space. Recursive pre-order traversal.
class Solution {
    fun trimBST(root: TreeNode?, low: Int, high: Int): TreeNode? {

        fun dfs(current: TreeNode?, parent: TreeNode, isLeft: Boolean) {
            var curr = current
            while (curr != null) {
                val newCurr: TreeNode?;
                if (curr.`val` < low) {
                    newCurr = curr.right
                } else if (curr.`val` > high) {
                    newCurr = curr.left
                } else {
                    break
                }
                if (isLeft) {
                    parent.left = newCurr
                } else {
                    parent.right = newCurr
                }
                curr = newCurr
            }
            if (curr == null) {
                return
            }
            dfs(curr.left, curr, true)
            dfs(curr.right, curr, false)
        }

        val dummy = TreeNode(0)
        dummy.left = root
        dfs(root, dummy, true)
        return dummy.left
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val root = case.args.root.deserialize()
            val actualRoot = Solution().trimBST(root, case.args.low, case.args.high)
            val actual = actualRoot.serialize()
            assertEquals(actual, case.expected)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: List<Int?>)

    @Serializable
    class Args(val root: List<Int?>, val low: Int, val high: Int)
}
