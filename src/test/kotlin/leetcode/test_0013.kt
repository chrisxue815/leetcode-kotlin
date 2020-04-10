package leetcode.test_0013

import kotlinx.serialization.Serializable
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals


// O(n) time. O(1) space. Observation.
class Solution {
    fun romanToInt(s: String): Int {
        val values = mapOf(
            'I' to 1,
            'V' to 5,
            'X' to 10,
            'L' to 50,
            'C' to 100,
            'D' to 500,
            'M' to 1000
        )

        var result = 0
        var prev = 0

        for (ch in s.reversed()) {
            val curr = values.getOrDefault(ch, 0)

            if (curr < prev) {
                result -= curr
            } else {
                result += curr
            }

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
            val actual = Solution().romanToInt(case.args.s)
            assertEquals(actual, case.expected)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: Int)

    @Serializable
    class Args(val s: String)
}
