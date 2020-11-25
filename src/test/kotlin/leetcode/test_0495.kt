@file:Suppress("PackageDirectoryMismatch")

package leetcode.test_0495

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals

// O(n) time. O(1) space. Array.
class Solution {
    fun findPoisonedDuration(timeSeries: IntArray, duration: Int): Int {
        if (timeSeries.isEmpty()) {
            return 0
        }

        var result = duration
        var prev = timeSeries[0]

        for (curr in timeSeries) {
            result += Integer.min(duration, curr - prev)
            prev = curr
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
            val actual = Solution().findPoisonedDuration(case.args.timeSeries, case.args.duration)
            assertEquals(case.expected, actual, msg)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: Int)

    @Serializable
    class Args(val timeSeries: IntArray, val duration: Int)
}
