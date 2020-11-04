package leetcode.test_0404

import kotlinx.serialization.Serializable
import leetcode.util.TreeNode
import leetcode.util.deserialize
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals

// O(n) time. O(log(n)) space. Recursive pre-order traversal.
class Solution {
    fun sumOfLeftLeaves(root: TreeNode?): Int {
        var sum = 0

        tailrec fun dfs(root: TreeNode?) {
            if (root == null) {
                return
            }

            val left = root.left

            if (left != null && left.left == null && left.right == null) {
                sum += left.`val`
            } else {
                @Suppress("NON_TAIL_RECURSIVE_CALL")
                dfs(left)
            }

            dfs(root.right)
        }

        dfs(root)

        return sum
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val root = case.args.root.deserialize()
            val actual = Solution().sumOfLeftLeaves(root)
            assertEquals(case.expected, actual)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: Int)

    @Serializable
    class Args(val root: List<Int?>)
}
