package leetcode.test_0623

import kotlinx.serialization.Serializable
import leetcode.util.TreeNode
import leetcode.util.deserialize
import leetcode.util.loadTestJson
import leetcode.util.serialize
import kotlin.test.Test
import kotlin.test.assertEquals


// O(n) time. O(log(n)) space. Recursive DFS.
class Solution {
    fun addOneRow(root: TreeNode?, v: Int, d: Int): TreeNode? {
        if (d == 1) {
            val parent = TreeNode(v)
            parent.left = root
            return parent
        }

        fun dfs(curr: TreeNode?, depth: Int) {
            if (curr == null) {
                return
            }
            if (depth != d - 1) {
                dfs(curr.left, depth + 1)
                dfs(curr.right, depth + 1)
            } else {
                val left = TreeNode(v)
                left.left = curr.left
                curr.left = left

                val right = TreeNode(v)
                right.right = curr.right
                curr.right = right
            }
        }

        dfs(root, 1)
        return root
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val root = case.args.root.deserialize()
            val actual = Solution().addOneRow(root, case.args.v, case.args.d)
            assertEquals(case.expected, actual.serialize())
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: List<Int?>)

    @Serializable
    class Args(val root: List<Int?>, val v: Int, val d: Int)
}
