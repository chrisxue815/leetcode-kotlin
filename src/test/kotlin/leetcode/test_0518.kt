package leetcode.test_0518

import kotlinx.serialization.Serializable
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals


// O(len(coins) * amount) time. O(amount) space. Space-optimized DP, unbounded knapsack.
class Solution {
    fun change(amount: Int, coins: IntArray): Int {
        val dp = IntArray(amount + 1)
        dp[0] = 1

        for (coin in coins) {
            for (j in coin..amount) {
                dp[j] += dp[j - coin]
            }
        }

        return dp.last()
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val actual = Solution().change(case.args.amount, case.args.coins)
            assertEquals(case.expected, actual)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: Int)

    @Serializable
    class Args(val amount: Int, val coins: IntArray)
}
