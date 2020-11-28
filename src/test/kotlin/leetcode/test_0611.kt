@file:Suppress("PackageDirectoryMismatch")

package leetcode.test_0611

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals


// O(n^2) time. O(1) space. Sort, counting pairs in a sorted array.
class Solution {
    fun triangleNumber(nums: IntArray): Int {
        var result = 0
        nums.sort()

        for (i in 2 until nums.size) {
            val c = nums[i]
            var lo = 0
            var hi = i - 1

            while (lo < hi) {
                if (nums[lo] + nums[hi] > c) {
                    result += hi - lo
                    hi--
                } else {
                    lo++
                }
            }
        }

        return result
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val msg = Json.encodeToString(Args.serializer(), case.args)
            val actual = Solution().triangleNumber(case.args.nums)
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
