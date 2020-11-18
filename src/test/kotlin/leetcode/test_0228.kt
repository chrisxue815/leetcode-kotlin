@file:Suppress("PackageDirectoryMismatch")

package leetcode.test_0228

import kotlinx.serialization.Serializable
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals

// O(n) time. O(1) space. Array.
class Solution {
    fun summaryRanges(nums: IntArray): List<String> {
        val result = ArrayList<String>()
        var lo = 0

        for (i in nums.indices) {
            if (i + 1 >= nums.size || nums[i] + 1 < nums[i + 1]) {
                if (lo == i) {
                    result.add(nums[i].toString())
                } else {
                    result.add("${nums[lo]}->${nums[i]}")
                }
                lo = i + 1
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
            val actual = Solution().summaryRanges(case.args.nums)
            assertEquals(case.expected, actual)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: List<String>)

    @Serializable
    class Args(val nums: IntArray)
}
