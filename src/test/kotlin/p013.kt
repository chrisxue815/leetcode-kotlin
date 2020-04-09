package leetcode.p013

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


// O(n) time. O(1) space. Observation.
class Solution {
    fun romanToInt(s: String): Int {
        val values = mapOf('I' to 1, 'V' to 5, 'X' to 10, 'L' to 50, 'C' to 100, 'D' to 500, 'M' to 1000)

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
    @ParameterizedTest
    @MethodSource("createTestData")
    fun test(nums: String, expected: Int) {
        val actual = Solution().romanToInt(nums)
        assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        fun createTestData(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of("I", 1),
                    Arguments.of("II", 2),
                    Arguments.of("III", 3),
                    Arguments.of("IV", 4),
                    Arguments.of("V", 5),
                    Arguments.of("IX", 9),
                    Arguments.of("XIV", 14),
                    Arguments.of("XLV", 45)
            )
        }
    }
}
