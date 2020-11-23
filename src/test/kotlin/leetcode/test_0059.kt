package leetcode.test_0059

import kotlinx.serialization.Serializable
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals

// O(n) time. O(1) space. Matrix.
class Solution {
    fun generateMatrix(n: Int): Array<IntArray> {
        val result = Array(n) { IntArray(n) }

        var lo = 0
        var hi = n - 1
        var i = 1

        while (lo <= hi) {
            for (c in lo..hi) {
                result[lo][c] = i
                i++
            }

            for (r in lo + 1..hi) {
                result[r][hi] = i
                i++
            }

            for (c in hi - 1 downTo lo) {
                result[hi][c] = i
                i++
            }

            for (r in hi - 1 downTo lo + 1) {
                result[r][lo] = i
                i++
            }

            lo++
            hi--
        }

        return result
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val actual = Solution().generateMatrix(case.args.n)
            val actualList = actual.map { it.asList() }.toList()
            assertEquals(case.expected, actualList)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: List<List<Int>>)

    @Serializable
    class Args(val n: Int)
}
