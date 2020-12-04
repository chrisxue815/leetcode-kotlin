@file:Suppress("PackageDirectoryMismatch")

package leetcode.test_0670

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals

// O(n) time. O(1) space. Math.
class Solution {
    fun maximumSwap(num: Int): Int {
        var i = 0
        var pending = num
        var maxDigit = -1
        var maxDigitIndex = -1
        var leftDigit = -1
        var leftDigitIndex = -1
        var rightDigit = -1
        var rightDigitIndex = -1

        while (pending > 0) {
            val digit = pending % 10
            pending /= 10

            if (digit > maxDigit) {
                maxDigit = digit
                maxDigitIndex = i
            } else if (digit < maxDigit) {
                leftDigit = digit
                leftDigitIndex = i
                rightDigit = maxDigit
                rightDigitIndex = maxDigitIndex
            }

            i++
        }

        if (leftDigitIndex == -1) {
            return num
        }

        var result = 0
        var multiple = 1
        i = 0
        pending = num
        while (pending > 0) {
            var digit = pending % 10
            pending /= 10

            if (i == leftDigitIndex) {
                digit = rightDigit
            } else if (i == rightDigitIndex) {
                digit = leftDigit
            }
            result += multiple * digit

            multiple *= 10
            i++
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
            val actual = Solution().maximumSwap(case.args.num)
            assertEquals(case.expected, actual, msg)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: Int)

    @Serializable
    class Args(val num: Int)
}
