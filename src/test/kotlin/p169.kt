package leetcode.p169

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


// O(n) time. O(1) space. Majority vote.
class Solution {
    fun majorityElement(nums: IntArray): Int {
        var major = 0
        var count = 0

        for (num in nums) {
            when {
                num == major -> count++
                count > 0 -> count--
                else -> {
                    major = num
                    count = 1
                }
            }
        }

        return major
    }
}

class SolutionTest {
    @ParameterizedTest
    @MethodSource("createTestData")
    fun test(nums: Array<Int>, expected: Int) {
        val actual = Solution().majorityElement(nums.toIntArray())
        assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        fun createTestData(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of(arrayOf(1, 2, 1), 1),
                    Arguments.of(arrayOf(1, 2, 1, 1, 3), 1),
                    Arguments.of(arrayOf(2, 3, 1, 1, 1), 1)
            )
        }
    }
}
