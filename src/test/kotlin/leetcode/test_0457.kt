package leetcode.test_0457

import kotlinx.serialization.Serializable
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals

// O(n) time. O(1) space. Floyd's tortoise and hare cycle detection algorithm.
class Solution {
    fun circularArrayLoop(nums: IntArray): Boolean {
        fun shouldAdvance(curr: Int, forward: Boolean): Boolean {
            return nums[curr] > 0 == forward && nums[curr] % nums.size != 0
        }

        fun advance(curr: Int): Int {
            return Math.floorMod(curr + nums[curr], nums.size)
        }

        for (start in nums.indices) {
            if (nums[start] == 0) {
                continue
            }

            var h = start
            var t = start
            val forward = nums[start] > 0

            while (true) {
                if (!shouldAdvance(h, forward)) {
                    break
                }
                h = advance(h)

                if (!shouldAdvance(h, forward)) {
                    break
                }
                h = advance(h)

                t = advance(t)

                if (t == h) {
                    return true
                }
            }

            t = start

            while (shouldAdvance(t, forward)) {
                val next = advance(t)
                nums[t] = 0
                t = next
            }
        }

        return false
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val actual = Solution().circularArrayLoop(case.args.nums)
            assertEquals(case.expected, actual)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: Boolean)

    @Serializable
    class Args(val nums: IntArray)
}
