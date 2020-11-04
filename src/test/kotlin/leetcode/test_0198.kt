package leetcode.test_0198

import kotlinx.serialization.Serializable
import leetcode.util.loadTestJson
import kotlin.math.max
import kotlin.test.Test
import kotlin.test.assertEquals


// O(n) time. O(n) space. DP.
class Solution {
    fun rob(nums: IntArray): Int {
        if (nums.size <= 2) {
            return nums.max() ?: 0
        }

        // dp[i]: rob(nums[0 : i + 1])
        val dp = IntArray(nums.size)
        dp[0] = nums[0]
        dp[1] = max(nums[0], nums[1])

        for (i in 2 until nums.size) {
            dp[i] = max(dp[i - 1], dp[i - 2] + nums[i])
        }

        return dp.last()
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val actual = Solution().rob(case.args.nums)
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
