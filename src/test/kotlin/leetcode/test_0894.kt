@file:Suppress("PackageDirectoryMismatch")

package leetcode.test_0894

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import leetcode.util.TreeNode
import leetcode.util.loadTestJson
import leetcode.util.serialize
import kotlin.test.Test
import kotlin.test.assertEquals

// Backtracking
class Solution {
    fun allPossibleFBT(N: Int): List<TreeNode?> {
        if (N and 1 == 0) {
            return emptyList()
        }
        if (N == 1) {
            return listOf(TreeNode(0))
        }

        val result = ArrayList<TreeNode?>()
        for (leftCount in 1 until N step 2) {
            for (left in allPossibleFBT(leftCount)) {
                for (right in allPossibleFBT(N - 1 - leftCount)) {
                    val curr = TreeNode(0)
                    curr.left = left // left.clone() to make each tree independent
                    curr.right = right
                    result.add(curr)
                }
            }
        }

        return result
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val msg = Json.encodeToString(Args.serializer(), case.args)
            val actualNodes = Solution().allPossibleFBT(case.args.N)
            val actual = actualNodes.map { it.serialize() }
            assertEquals(case.expected, actual, msg)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: List<List<Int?>>)

    @Serializable
    class Args(val N: Int)
}
