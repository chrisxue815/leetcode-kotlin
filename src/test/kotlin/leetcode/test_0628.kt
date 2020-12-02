@file:Suppress("PackageDirectoryMismatch")

package leetcode.test_0628

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import leetcode.util.loadTestJson
import java.util.Collections
import java.util.PriorityQueue
import kotlin.test.Test
import kotlin.test.assertEquals

// O(n) time. O(1) space. Math, binary heap.
class Solution {
    fun maximumProduct(nums: IntArray): Int {
        val maxNums = PriorityQueue<Int>(3)
        val minNums = PriorityQueue<Int>(3, Collections.reverseOrder())

        for (num in nums) {
            when {
                maxNums.size < 3 -> {
                    maxNums.offer(num)
                    minNums.offer(num)
                }
                num > maxNums.peek() -> {
                    maxNums.poll() // replace() not supported by Java PriorityQueue :(
                    maxNums.offer(num)
                }
                num < minNums.peek() -> {
                    minNums.poll() // replace() not supported by Java PriorityQueue :(
                    minNums.offer(num)
                }
            }
        }

        val thirdLargest = maxNums.poll()
        val secondLargest = maxNums.poll()
        val largest = maxNums.poll()

        minNums.poll()
        val secondSmallest = minNums.poll()
        val smallest = minNums.poll()

        return Integer.max(largest * secondLargest * thirdLargest, largest * smallest * secondSmallest)
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val msg = Json.encodeToString(Args.serializer(), case.args)
            val actual = Solution().maximumProduct(case.args.nums)
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
