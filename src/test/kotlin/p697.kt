package leetcode.p697

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

// O(n) time. O(n) space. Hash table.
class Solution {
    fun findShortestSubArray(nums: IntArray): Int {
        if (nums.isEmpty()) {
            return 0
        }

        val counts = HashMap<Int, Int>()
        val ranges = HashMap<Int, IntArray>()

        for ((i, num) in nums.withIndex()) {
            counts[num] = counts.getOrDefault(num, 0) + 1

            val range = ranges[num]
            if (range == null) {
                ranges[num] = intArrayOf(i, i)
            } else {
                range[1] = i
            }
        }

        val degree = counts.maxBy { it.value }!!.value
        var minRange = Int.MAX_VALUE

        for ((num, count) in counts) {
            if (count == degree) {
                val range = ranges[num]!!
                minRange = Math.min(minRange, range[1] - range[0])
            }
        }

        return minRange + 1
    }
}

class SolutionTest {
    @ParameterizedTest
    @MethodSource("createTestData")
    fun test(nums: Array<Int>, expected: Int) {
        val actual = Solution().findShortestSubArray(nums.toIntArray())
        assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        fun createTestData(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of(arrayOf(1, 2, 3), 1),
                    Arguments.of(arrayOf(1, 2, 2, 3, 1), 2),
                    Arguments.of(arrayOf(1, 2, 2, 3, 1, 4, 2), 6),
                    Arguments.of(arrayOf(2, 2, 1, 4, 1, 3, 3), 2)
            )
        }
    }
}
