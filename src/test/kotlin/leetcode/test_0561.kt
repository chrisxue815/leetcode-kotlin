@file:Suppress("PackageDirectoryMismatch")

package leetcode.test_0561

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals

// O(nlog(n)) time. O(1) space. Sort.
class Solution {
    fun arrayPairSum(nums: IntArray): Int {
        nums.sort()
        return nums.filterIndexed { index, _ -> index and 1 == 0 }.sum()
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val msg = Json.encodeToString(Args.serializer(), case.args)
            val actual = Solution().arrayPairSum(case.args.nums)
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
