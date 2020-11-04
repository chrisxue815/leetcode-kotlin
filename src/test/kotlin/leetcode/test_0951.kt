package leetcode.test_0951

import kotlinx.serialization.Serializable
import leetcode.util.TreeNode
import leetcode.util.deserialize
import leetcode.util.loadTestJson
import leetcode.util.serialize
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

// O(n) time. O(log(n)) space. Recursive pre-order traversal.
class Solution {
    fun flipEquiv(root1: TreeNode?, root2: TreeNode?): Boolean {
        if (root1 == null && root2 == null) {
            return true
        }
        if (root1 != null && root2 != null) {
            return root1.`val` == root2.`val`
                    && (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)
                    || flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left))
        }
        return false
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val root1 = case.args.root1.deserialize()
            val root2 = case.args.root2.deserialize()
            val actual = Solution().flipEquiv(root1, root2)
            assertEquals(case.expected, actual)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: Boolean)

    @Serializable
    class Args(val root1: List<Int?>, val root2: List<Int?>)
}
