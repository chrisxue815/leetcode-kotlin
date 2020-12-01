@file:Suppress("PackageDirectoryMismatch")

package leetcode.test_0605

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals

// O(n) time. O(1) space. Array.
class Solution {
    fun canPlaceFlowers(flowerbed: IntArray, n: Int): Boolean {
        if (n <= 0) {
            return true
        }

        var pending = n
        var zeros = 1

        for (planted in flowerbed) {
            if (planted == 0) {
                zeros++
                continue
            }

            pending -= (zeros - 1) / 2
            if (pending <= 0) {
                return true
            }
            zeros = 0
        }

        pending -= zeros / 2
        return pending <= 0
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val msg = Json.encodeToString(Args.serializer(), case.args)
            val actual = Solution().canPlaceFlowers(case.args.flowerbed, case.args.n)
            assertEquals(case.expected, actual, msg)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: Boolean)

    @Serializable
    class Args(val flowerbed: IntArray, val n: Int)
}
