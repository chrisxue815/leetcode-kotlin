package leetcode.test_0563

import kotlinx.serialization.Serializable
import leetcode.util.TreeNode
import leetcode.util.deserialize
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals

// O(n) time. O(log(n)) space. Recursive post-order traversal.
class Solution {
    fun findTilt(root: TreeNode?): Int {
        var tilt = 0

        fun dfs(root: TreeNode?): Int {
            if (root == null) {
                return 0
            }

            val leftSum = dfs(root.left)
            val rightSum = dfs(root.right)
            tilt += Math.abs(leftSum - rightSum)
            return root.`val` + leftSum + rightSum
        }

        dfs(root)

        return tilt
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val root = case.args.root.deserialize()
            val actual = Solution().findTilt(root)
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
