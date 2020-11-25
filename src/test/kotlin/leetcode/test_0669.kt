@file:Suppress("PackageDirectoryMismatch")

package leetcode.test_0669

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import leetcode.util.TreeNode
import leetcode.util.deserialize
import leetcode.util.loadTestJson
import leetcode.util.serialize
import kotlin.test.Test
import kotlin.test.assertEquals

// O(n) time. O(log(n)) space. Recursive pre-order traversal.
class Solution {
    fun trimBST(root: TreeNode?, low: Int, high: Int): TreeNode? {
        fun trim(curr: TreeNode?): TreeNode? {
            return when {
                curr == null -> {
                    null
                }
                curr.`val` < low -> {
                    trim(curr.right)
                }
                curr.`val` > high -> {
                    trim(curr.left)
                }
                else -> {
                    curr.left = trim(curr.left)
                    curr.right = trim(curr.right)
                    curr
                }
            }
        }
        return trim(root)
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val msg = Json.encodeToString(Args.serializer(), case.args)
            val root = case.args.root.deserialize()
            val actualRoot = Solution().trimBST(root, case.args.low, case.args.high)
            val actual = actualRoot.serialize()
            assertEquals(case.expected, actual, msg)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: List<Int?>)

    @Serializable
    class Args(val root: List<Int?>, val low: Int, val high: Int)
}
