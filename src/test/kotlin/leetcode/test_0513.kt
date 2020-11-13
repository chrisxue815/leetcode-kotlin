package leetcode.test_0513

import kotlinx.serialization.Serializable
import leetcode.util.TreeNode
import leetcode.util.deserialize
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals


// O(n) time. O(log(n)) space. Recursive DFS.
class Solution {
    fun findBottomLeftValue(root: TreeNode?): Int {
        var result = 0
        var maxDepth = 0

        fun dfs(curr: TreeNode?, depth: Int) {
            if (curr == null) {
                return
            }

            if (maxDepth < depth) {
                maxDepth = depth
                result = curr.`val`
            }

            dfs(curr.left, depth + 1)
            dfs(curr.right, depth + 1)
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
            val actual = Solution().findBottomLeftValue(root)
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
