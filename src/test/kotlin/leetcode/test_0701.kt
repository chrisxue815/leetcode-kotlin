package leetcode.test_0701

import kotlinx.serialization.Serializable
import leetcode.util.TreeNode
import leetcode.util.deserialize
import leetcode.util.loadTestJson
import leetcode.util.serialize
import kotlin.test.Test
import kotlin.test.assertTrue

// O(n) time. O(log(n)) space. Recursive pre-order traversal.
class Solution {
    fun insertIntoBST(root: TreeNode?, `val`: Int): TreeNode? {
        return if (root != null) {
            if (`val` < root.`val`) {
                root.left = insertIntoBST(root.left, `val`)
            } else {
                root.right = insertIntoBST(root.right, `val`)
            }
            root
        } else {
            TreeNode(`val`)
        }
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val root = case.args.root.deserialize()
            val actualRoot = Solution().insertIntoBST(root, case.args.`val`)
            val actual = actualRoot.serialize()
            assertTrue(case.expected.contains(actual))
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: List<List<Int?>>)

    @Serializable
    class Args(val root: List<Int?>, val `val`: Int)
}
