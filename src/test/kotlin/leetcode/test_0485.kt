package leetcode.test_0485

import kotlinx.serialization.Serializable
import leetcode.util.loadTestJson
import java.lang.Integer.max
import kotlin.test.Test
import kotlin.test.assertEquals

// O(n) time. O(1) space. Array.
class Solution {
    fun findMaxConsecutiveOnes(nums: IntArray): Int {
        var result = 0
        var curr = 0
        for (num in nums) {
            if (num == 1) {
                curr++
            } else {
                result = max(result, curr)
                curr = 0
            }
        }
        return max(result, curr)
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val actual = Solution().findMaxConsecutiveOnes(case.args.nums)
            assertEquals(case.expected, actual)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: Int)

    @Serializable
    class Args(val nums: IntArray)
}