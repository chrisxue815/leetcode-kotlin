@file:Suppress("PackageDirectoryMismatch")

package leetcode.test_0485

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals

// O(n) time. O(1) space. Array.
class Solution {
    fun findMaxConsecutiveOnes(nums: IntArray): Int {
        var result = 0
        var curr = 0
        for (num in nums) {
            if (num == 1) {
                curr++
            } else {
                result = Integer.max(result, curr)
                curr = 0
            }
        }
        return Integer.max(result, curr)
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val msg = Json.encodeToString(Args.serializer(), case.args)
            val actual = Solution().findMaxConsecutiveOnes(case.args.nums)
            assertEquals(case.expected, actual, msg)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: Int)

    @Serializable
    class Args(val nums: IntArray)
}
