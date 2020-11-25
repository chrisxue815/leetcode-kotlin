@file:Suppress("PackageDirectoryMismatch")

package leetcode.test_0100

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import leetcode.util.TreeNode
import leetcode.util.deserialize
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals


// O(n) time. O(log(n)) space. Recursive pre-order traversal.
class Solution {
    tailrec fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null || q == null) {
            return p === q
        }

        if (p.`val` != q.`val`) return false

        @Suppress("NON_TAIL_RECURSIVE_CALL")
        if (!isSameTree(p.left, q.left)) return false

        return isSameTree(p.right, q.right)
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val msg = Json.encodeToString(Args.serializer(), case.args)
            val p = case.args.p.deserialize()
            val q = case.args.q.deserialize()
            val actual = Solution().isSameTree(p, q)
            assertEquals(case.expected, actual, msg)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: Boolean)

    @Serializable
    class Args(val p: List<Int?>, val q: List<Int?>)
}
