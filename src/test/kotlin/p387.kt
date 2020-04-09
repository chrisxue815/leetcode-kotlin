package leetcode.p387

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


// O(n) time. O(n) space. Hash table.
class Solution {
    fun firstUniqChar(s: String): Int {
        val counter = s.groupingBy { it }.eachCount()

        for ((i, ch) in s.withIndex()) {
            if (counter.getOrDefault(ch, 0) == 1) {
                return i
            }
        }

        return -1
    }
}

class SolutionTest {
    @ParameterizedTest
    @MethodSource("createTestData")
    fun test(s: String, expected: Int) {
        val actual = Solution().firstUniqChar(s)
        assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        fun createTestData(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of("leetcode", 0),
                    Arguments.of("loveleetcode", 2),
                    Arguments.of("aabb", -1)
            )
        }
    }
}
