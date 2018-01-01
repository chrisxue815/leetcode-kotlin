package leetcode.p242

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


// O(n) time. O(1) space. Counting.
class Solution {
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) {
            return false
        }

        val count = IntArray(26)

        for (ch in s) {
            count[ch - 'a']++
        }

        for (ch in t) {
            val i = ch - 'a'
            if (count[i] <= 0) {
                return false
            }
            count[i]--
        }

        return true
    }
}

class SolutionTest {
    @ParameterizedTest
    @MethodSource("createTestData")
    fun test(s: String, t: String, expected: Boolean) {
        val actual = Solution().isAnagram(s, t)
        assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        fun createTestData(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of("anagram", "nagaram", true),
                    Arguments.of("rat", "cat", false)
            )
        }
    }
}
