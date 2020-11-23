package leetcode.test_0054

import kotlinx.serialization.Serializable
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals

// O(n) time. O(1) space. Matrix.
class Solution {
    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        val height = matrix.size
        val width = matrix[0].size
        val n = height * width
        val result = ArrayList<Int>(n)
        var top = 0
        var bottom = height - 1
        var left = 0
        var right = width - 1

        while (true) {
            for (c in left..right) {
                result.add(matrix[top][c])
            }
            if (result.size >= n) {
                break
            }
            top++

            for (r in top..bottom) {
                result.add(matrix[r][right])
            }
            if (result.size >= n) {
                break
            }
            right--

            for (c in right downTo left) {
                result.add(matrix[bottom][c])
            }
            if (result.size >= n) {
                break
            }
            bottom--

            for (r in bottom downTo top) {
                result.add(matrix[r][left])
            }
            if (result.size >= n) {
                break
            }
            left++
        }

        return result
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val actual = Solution().spiralOrder(case.args.matrix)
            assertEquals(case.expected, actual)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: List<Int>)

    @Serializable
    class Args(val matrix: Array<IntArray>)
}
