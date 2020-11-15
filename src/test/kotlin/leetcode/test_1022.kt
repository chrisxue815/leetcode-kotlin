package leetcode.test_1022

import kotlinx.serialization.Serializable
import leetcode.util.TreeNode
import leetcode.util.deserialize
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals

// O(n) time. O(log(n)) space. Recursive pre-order DFS, bit manipulation.
class Solution {
    fun sumRootToLeaf(root: TreeNode?): Int {
        var sum = 0

        fun dfs(curr: TreeNode, parentNum: Int) {
            val left = curr.left
            val right = curr.right
            val num = (parentNum shl 1) or curr.`val`

            if (left != null) {
                dfs(left, num)
            }
            if (right != null) {
                dfs(right, num)
            }
            if (left == null && right == null) {
                sum += num
            }
        }

        if (root != null) {
            dfs(root, 0)
        }

        return sum
    }
}


class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val root = case.args.root.deserialize()
            val actual = Solution().sumRootToLeaf(root)
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
