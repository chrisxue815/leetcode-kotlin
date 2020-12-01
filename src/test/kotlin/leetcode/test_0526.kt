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
        val pending = IntArray(N) { it + 1 }

        fun dfs(i: Int) {
            if (i >= N) {
                result++
                return
            }

            val index = i + 1

            for (j in i until N) {
                val num = pending[j]
                if (num == index || num > index && num % index == 0 || num < index && index % num == 0) {
                    pending[i] = pending[j].also { pending[j] = pending[i] }
                    dfs(i + 1)
                    pending[i] = pending[j].also { pending[j] = pending[i] }
                }
            }
        }

        dfs(0)
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
