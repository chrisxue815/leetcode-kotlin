package leetcode.test_0387

import kotlinx.serialization.Serializable
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals


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
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val actual = Solution().firstUniqChar(case.args.s)
            assertEquals(actual, case.expected)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: Int)

    @Serializable
    class Args(val s: String)
}
