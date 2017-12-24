package leetcode.p383

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


// O(n) time. O(n) space. Hash table.
class Solution {
    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        val magazineChars = HashMap<Char, Int>()

        for (ch in magazine) {
            magazineChars[ch] = magazineChars.getOrDefault(ch, 0) + 1
        }

        for (ch in ransomNote) {
            val count = magazineChars.getOrDefault(ch, 0)
            if (count <= 0) {
                return false
            }
            magazineChars[ch] = count - 1
        }

        return true
    }
}

class SolutionTest {
    @ParameterizedTest
    @MethodSource("createTestData")
    fun test(nums1: String, nums2: String, expected: Boolean) {
        val actual = Solution().canConstruct(nums1, nums2)
        assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        fun createTestData(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of("a", "b", false),
                    Arguments.of("aa", "ab", false),
                    Arguments.of("aa", "aab", true)
            )
        }
    }
}
