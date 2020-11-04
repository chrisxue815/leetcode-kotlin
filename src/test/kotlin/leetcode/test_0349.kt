package leetcode.test_0349

import kotlinx.serialization.Serializable
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals


// O(n) time. O(n) space. Hash table.
class Solution {
    fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
        val set1 = nums1.toHashSet()
        set1.retainAll(nums2.toHashSet())
        return set1.toIntArray()
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val actual = Solution().intersection(case.args.nums1, case.args.nums2)
            assertEquals(case.expected, actual.toList())
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: List<Int>)

    @Serializable
    class Args(val nums1: IntArray, val nums2: IntArray)
}
