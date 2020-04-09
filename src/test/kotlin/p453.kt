package leetcode.p453

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


// O(n) time. O(1) space. Math.
class Solution {
    fun minMoves(nums: IntArray): Int {
        return nums.sum() - nums.size * nums.min()!!
    }
}

class SolutionTest {
    @ParameterizedTest
    @MethodSource("createTestData")
    fun test(nums: Array<Int>, expected: Int) {
        val actual = Solution().minMoves(nums.toIntArray())
        assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        fun createTestData(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of(arrayOf(1, 2, 3), 3),
                    Arguments.of(arrayOf(1, 2), 1)
            )
        }
    }
}
