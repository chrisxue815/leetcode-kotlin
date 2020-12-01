@file:Suppress("PackageDirectoryMismatch")

package leetcode.test_0526

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals

// O(n!) time. O(n) space. Backtracking.
class Solution {
    fun countArrangement(N: Int): Int {
        var result = 0
        val used = BooleanArray(N + 1)

        fun dfs(i: Int) {
            if (i > N) {
                result++
            }

            for (num in 1..N) {
                if (used[num]) {
                    continue
                }

                if (num == i || num > i && num % i == 0 || num < i && i % num == 0) {
                    used[num] = true
                    dfs(i + 1)
                    used[num] = false
                }
            }
        }

        dfs(1)
        return result
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val msg = Json.encodeToString(Args.serializer(), case.args)
            val actual = Solution().countArrangement(case.args.N)
            assertEquals(case.expected, actual, msg)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: Int)

    @Serializable
    class Args(val N: Int)
}
