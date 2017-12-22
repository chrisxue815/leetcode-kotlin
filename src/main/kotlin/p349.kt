package leetcode.p349

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


// O(n) time. O(n) space. Hash table.
class Solution {
    fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
        val set1 = nums1.toHashSet()
        set1.retainAll(nums2.toHashSet())
        return set1.toIntArray()
    }
}

class SolutionTest {
    @ParameterizedTest
    @MethodSource("createTestData")
    fun test(nums1: Array<Int>, nums2: Array<Int>, expected: Array<Int>) {
        val actual = Solution().intersection(nums1.toIntArray(), nums2.toIntArray())
        assertArrayEquals(expected.toIntArray(), actual)
    }

    companion object {
        @JvmStatic
        fun createTestData(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of(arrayOf(1, 2, 2, 1), arrayOf(2, 2), arrayOf(2))
            )
        }
    }
}
