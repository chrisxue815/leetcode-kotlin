package leetcode.test_0565

import kotlinx.serialization.Serializable
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals

// O(n) time. O(1) space. Ring detection.
class Solution {
    fun arrayNesting(nums: IntArray): Int {
        var result = 0
        for (start in nums.indices) {
            if (nums[start] == -1) {
                continue
            }

            var size = 0
            var i = start
            while (nums[i] != -1) {
                size++
                val next = nums[i]
                nums[i] = -1
                i = next
            }
            result = Integer.max(result, size)
        }
        return result
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val actual = Solution().arrayNesting(case.args.nums)
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
