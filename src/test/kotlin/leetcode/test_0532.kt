package leetcode.test_0532

import kotlinx.serialization.Serializable
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals

// O(n) time. O(n) space. Hash table.
class Solution {
    fun findPairs(nums: IntArray, k: Int): Int {
        val counter = HashMap<Int, Int>()
        for (num in nums) {
            counter.compute(num) { _, old -> old?.plus(1) ?: 1 }
        }

        return if (k == 0) {
            counter.filter { it.value >= 2 }.count()
        } else {
            counter.keys.filter { counter.containsKey(it - k) }.count()
        }
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val actual = Solution().findPairs(case.args.nums, case.args.k)
            assertEquals(case.expected, actual)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: Int)

    @Serializable
    class Args(val nums: IntArray, val k: Int)
}
