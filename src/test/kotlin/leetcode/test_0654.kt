package leetcode.test_0654

import kotlinx.serialization.Serializable
import leetcode.util.TreeNode
import leetcode.util.loadTestJson
import leetcode.util.serialize
import kotlin.test.Test
import kotlin.test.assertEquals

// O(nlog(n)) time. O(log(n)) space. Recursive post-order DFS.
class Solution {
    fun constructMaximumBinaryTree(nums: IntArray): TreeNode? {
        fun construct(lo: Int, hi: Int): TreeNode? {
            if (lo >= hi) {
                return null
            }

            var max = nums[lo]
            var maxIndex = lo
            for (i in lo + 1 until hi) {
                if (max < nums[i]) {
                    max = nums[i]
                    maxIndex = i
                }
            }

            val root = TreeNode(max)
            root.left = construct(lo, maxIndex)
            root.right = construct(maxIndex + 1, hi)

            return root
        }

        return construct(0, nums.size)
    }
}


class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val actual = Solution().constructMaximumBinaryTree(case.args.nums)
            val actualValues = actual.serialize()
            assertEquals(case.expected, actualValues)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: List<Int?>)

    @Serializable
    class Args(val nums: IntArray)
}
