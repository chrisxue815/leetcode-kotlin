@file:Suppress("PackageDirectoryMismatch")

package leetcode.test_0667

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals

// O(n) time. O(1) space. Math.
class Solution {
    fun constructArray(n: Int, k: Int): IntArray {
        val result = IntArray(n)

        for (i in 0 until n - k) {
            result[i] = i + 1
        }

        for (i in 0 until k) {
            result[n - k + i] = if (i and 1 == 0) {
                n - (i shr 1)
            } else {
                n - k + 1 + (i shr 1)
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
            val msg = Json.encodeToString(Args.serializer(), case.args)
            val actual = Solution().constructArray(case.args.n, case.args.k)
            assertEquals(case.expected.asList(), actual.asList(), msg)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: IntArray)

    @Serializable
    class Args(val n: Int, val k: Int)
}
