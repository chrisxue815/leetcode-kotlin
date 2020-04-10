package leetcode.test_0169

import kotlinx.serialization.Serializable
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals


// O(n) time. O(1) space. Majority vote.
class Solution {
    fun majorityElement(nums: IntArray): Int {
        var major = 0
        var count = 0

        for (num in nums) {
            when {
                num == major -> count++
                count > 0 -> count--
                else -> {
                    major = num
                    count = 1
                }
            }
        }

        return major
    }
}


class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val actual = Solution().majorityElement(case.args.nums)
            assertEquals(actual, case.expected)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: Int)

    @Serializable
    class Args(val nums: IntArray)
}
