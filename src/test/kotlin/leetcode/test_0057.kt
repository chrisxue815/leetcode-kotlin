@file:Suppress("PackageDirectoryMismatch")

package leetcode.test_0057

import kotlinx.serialization.Serializable
import leetcode.util.loadTestJson
import java.lang.Integer.max
import java.lang.Integer.min
import kotlin.test.Test
import kotlin.test.assertEquals

// O(n) time. O(1) space. Interval.
class Solution {
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        val result = ArrayList<IntArray>()
        var i = 0

        while (i < intervals.size && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i])
            i++
        }
        val loIndex = i

        while (i < intervals.size && intervals[i][0] <= newInterval[1]) {
            i++
        }
        if (loIndex < intervals.size && i > 0) {
            val lo = min(intervals[loIndex][0], newInterval[0])
            val hi = max(intervals[i - 1][1], newInterval[1])
            result.add(intArrayOf(lo, hi))
        } else {
            result.add(newInterval)
        }

        while (i < intervals.size) {
            result.add(intervals[i])
            i++
        }

        return result.toTypedArray()
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val actual = Solution().insert(case.args.intervals, case.args.newInterval)
            val actualString = actual.joinToString(",") { it.contentToString() }
            val expectedString = case.expected.joinToString(",") { it.contentToString() }
            assertEquals(expectedString, actualString)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: Array<IntArray>)

    @Serializable
    class Args(val intervals: Array<IntArray>, val newInterval: IntArray)
}
