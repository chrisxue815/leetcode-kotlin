package leetcode.test_0652

import kotlinx.serialization.Serializable
import leetcode.util.TreeNode
import leetcode.util.deserialize
import leetcode.util.loadTestJson
import leetcode.util.serialize
import kotlin.test.Test
import kotlin.test.assertTrue

data class TreeKey(val value: Int, val leftId: Int, val rightId: Int)

// O(n) time. O(n) space. Recursive DFS, hash map.
class Solution {
    fun findDuplicateSubtrees(root: TreeNode?): List<TreeNode?> {
        val result = ArrayList<TreeNode?>()
        val ids = HashMap<TreeKey, Int>()
        val counts = HashMap<Int, Int>()

        fun dfs(curr: TreeNode?): Int {
            if (curr == null) {
                return -1
            }

            val id = ids.getOrPut(TreeKey(curr.`val`, dfs(curr.left), dfs(curr.right)), { ids.size })
            val count = counts.compute(id) { _, oldValue -> (oldValue ?: 0) + 1 }
            if (count == 2) {
                result.add(curr)
            }
            return id
        }

        dfs(root)
        return result
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val root = case.args.root.deserialize()
            val actual = Solution().findDuplicateSubtrees(root)
            val actualValues = actual.map { it.serialize() }
            assertTrue(case.expected.size == actualValues.size && case.expected.containsAll(actualValues), "${case.expected}\n$actualValues")
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: List<List<Int?>>)

    @Serializable
    class Args(val root: List<Int?>)
}
