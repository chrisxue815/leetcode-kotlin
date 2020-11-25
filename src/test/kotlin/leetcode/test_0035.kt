@file:Suppress("PackageDirectoryMismatch")

package leetcode.test_0035

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals

// O(log(n)) time. O(1) space. Binary search.
class Solution {
    fun searchInsert(nums: IntArray, target: Int): Int {
        val index = nums.binarySearch(target)
        return if (index >= 0) {
            index
        } else {
            -(index + 1)
        }
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val msg = Json.encodeToString(Args.serializer(), case.args)
            val actual = Solution().searchInsert(case.args.nums, case.args.target)
            assertEquals(case.expected, actual, msg)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: Int)

    @Serializable
    class Args(val nums: IntArray, val target: Int)
}
