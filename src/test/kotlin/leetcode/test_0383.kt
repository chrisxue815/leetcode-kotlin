@file:Suppress("PackageDirectoryMismatch")

package leetcode.test_0383

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals


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
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val msg = Json.encodeToString(Args.serializer(), case.args)
            val actual = Solution().canConstruct(case.args.ransomNote, case.args.magazine)
            assertEquals(case.expected, actual, msg)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: Boolean)

    @Serializable
    class Args(val ransomNote: String, val magazine: String)
}
