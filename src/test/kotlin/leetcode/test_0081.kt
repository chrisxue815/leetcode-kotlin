@file:Suppress("PackageDirectoryMismatch")

package leetcode.test_0081

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals

// O(log(n)) average, O(n) worst-case time. O(1) space. Binary search.
class Solution {
    fun search(nums: IntArray, target: Int): Boolean {
        var lo = 0
        var hi = nums.size - 1

        while (lo <= hi) {
            val mid = lo + ((hi - lo) shr 1)

            if (nums[mid] == target) {
                return true
            }

            when {
                nums[mid] < nums[lo] -> {
                    if (nums[mid] <= target && target <= nums[hi]) {
                        lo = mid + 1
                    } else {
                        hi = mid - 1
                    }
                }
                nums[mid] > nums[lo] -> {
                    if (nums[lo] <= target && target < nums[mid]) {
                        hi = mid - 1
                    } else {
                        lo = mid + 1
                    }
                }
                else -> {
                    lo++
                }
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
            val msg = Json.encodeToString(Args.serializer(), case.args)
            val actual = Solution().search(case.args.nums, case.args.target)
            assertEquals(case.expected, actual, msg)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: Boolean)

    @Serializable
    class Args(val nums: IntArray, val target: Int)
}
