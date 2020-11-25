@file:Suppress("PackageDirectoryMismatch")

package leetcode.test_0494

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals


// O(len(nums) * (sum(nums) + max(nums))) time. O(len(nums) * (sum(nums) + max(nums))) space. DP, 0-1 knapsack.
class Solution {
    fun findTargetSumWays(nums: IntArray, S: Int): Int {
        if (nums.isEmpty()) {
            return if (S == 0) {
                1
            } else {
                0
            }
        }

        val sum = nums.sum()
        if (S < -sum || S > sum) {
            return 0
        }

        val max = nums.max()!!
        val bound = sum + max
        val range = bound * 2 + 1
        val dp = Array(nums.size + 1) { IntArray(range) }
        dp[0][bound] = 1

        for (i in 1..nums.size) {
            val num = nums[i - 1]
            for (j in -sum..sum) {
                dp[i][j + bound] = dp[i - 1][j - num + bound] + dp[i - 1][j + num + bound]
            }
        }

        return dp[nums.size][S + bound]
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val msg = Json.encodeToString(Args.serializer(), case.args)
            val actual = Solution().findTargetSumWays(case.args.nums, case.args.S)
            assertEquals(case.expected, actual, msg)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: Int)

    @Serializable
    class Args(val nums: IntArray, val S: Int)
}
