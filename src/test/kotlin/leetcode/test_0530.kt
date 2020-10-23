package leetcode.test_0530

import kotlinx.serialization.Serializable
import leetcode.util.TreeNode
import leetcode.util.deserialize
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals


// O(n) time. O(log(n)) space. Recursive in-order traversal.
class Solution {
    fun getMinimumDifference(root: TreeNode?): Int {
        var minDiff = Int.MAX_VALUE
        var prev = -1

        tailrec fun dfs(root: TreeNode?) {
            if (root == null) {
                return
            }

            @Suppress("NON_TAIL_RECURSIVE_CALL")
            dfs(root.left)

            if (prev != -1) {
                minDiff = Math.min(minDiff, root.`val` - prev)
            }

            prev = root.`val`

            dfs(root.right)
        }

        dfs(root)

        return minDiff
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val root = case.args.root.deserialize()
            val actual = Solution().getMinimumDifference(root)
            assertEquals(actual, case.expected)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: Int)

    @Serializable
    class Args(val root: List<Int?>)
}