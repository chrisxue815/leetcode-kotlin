package leetcode.test_0993

import kotlinx.serialization.Serializable
import leetcode.util.TreeNode
import leetcode.util.deserialize
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals

// O(n) time. O(log(n)) space. Recursive pre-order traversal, bit manipulation.
class Solution {
    fun isCousins(root: TreeNode?, x: Int, y: Int): Boolean {
        var index1 = -1
        var result = false

        fun dfs(curr: TreeNode?, index: Int): Boolean {
            if (curr == null) {
                return false
            }

            if (curr.`val` == x || curr.`val` == y) {
                if (index1 == -1) {
                    index1 = index
                } else {
                    result = Integer.numberOfLeadingZeros(index) == Integer.numberOfLeadingZeros(index1) && index != (index1 or 1)
                    return true
                }
            }

            return dfs(curr.left, index shl 1) || dfs(curr.right, (index shl 1) + 1)
        }

        dfs(root, 1)
        return result
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val root = case.args.root.deserialize()
            val actual = Solution().isCousins(root, case.args.x, case.args.y)
            assertEquals(case.expected, actual)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: Boolean)

    @Serializable
    class Args(val root: List<Int?>, val x: Int, val y: Int)
}
