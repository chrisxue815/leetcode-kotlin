@file:Suppress("PackageDirectoryMismatch")

package leetcode.test_0242

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals

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
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val msg = Json.encodeToString(Args.serializer(), case.args)
            val actual = Solution().isAnagram(case.args.s, case.args.t)
            assertEquals(case.expected, actual, msg)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: Boolean)

    @Serializable
    class Args(val s: String, val t: String)
}
