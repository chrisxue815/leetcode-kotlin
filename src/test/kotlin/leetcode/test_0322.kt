package leetcode.test_0322

import kotlinx.serialization.Serializable
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals


// O(len(coins) * amount) time. O(amount) space. Space-optimized DP, unbounded knapsack.
class Solution {
    fun coinChange(coins: IntArray, amount: Int): Int {
        val maxNumCoins = amount + 1
        val dp = IntArray(maxNumCoins) { maxNumCoins }
        dp[0] = 0

        for (coin in coins) {
            for (j in coin..amount) {
                dp[j] = Integer.min(dp[j], dp[j - coin] + 1)
            }
        }

        return if (dp[amount] < maxNumCoins) {
            dp[amount]
        } else {
            -1
        }
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val actual = Solution().coinChange(case.args.coins, case.args.amount)
            assertEquals(case.expected, actual)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: Int)

    @Serializable
    class Args(val coins: IntArray, val amount: Int)
}
