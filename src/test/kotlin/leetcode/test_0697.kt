package leetcode.test_0697

import kotlinx.serialization.Serializable
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals

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
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val actual = Solution().findShortestSubArray(case.args.nums)
            assertEquals(case.expected, actual)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: Int)

    @Serializable
    class Args(val nums: IntArray)
}
