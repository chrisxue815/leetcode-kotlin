@file:Suppress("PackageDirectoryMismatch")

package leetcode.test_1051

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals

// O(nlog(n)) time. O(n) space. Sort.
class Solution {
    fun heightChecker(heights: IntArray): Int {
        val sorted = heights.sortedArray()
        var count = 0
        for (i in heights.indices) {
            if (heights[i] != sorted[i]) {
                count++
            }
        }
        return count
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val msg = Json.encodeToString(Args.serializer(), case.args)
            val actual = Solution().heightChecker(case.args.heights)
            assertEquals(case.expected, actual, msg)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: Int)

    @Serializable
    class Args(val heights: IntArray)
}
