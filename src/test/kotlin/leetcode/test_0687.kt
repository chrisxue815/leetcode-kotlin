package leetcode.test_0687

import kotlinx.serialization.Serializable
import leetcode.util.TreeNode
import leetcode.util.deserialize
import leetcode.util.loadTestJson
import kotlin.math.max
import kotlin.test.Test
import kotlin.test.assertEquals

data class Result(val height: Int, val length: Int)

fun dfs(root: TreeNode?): Result {
    if (root == null) {
        return Result(0, 0)
    }

    var length = 0

    val left = root.left
    var (leftHeight, leftLength) = dfs(left)
    if (left?.`val` == root.`val`) {
        leftHeight++
        length = leftHeight
    } else {
        leftHeight = 0
    }

    val right = root.right
    var (rightHeight, rightLength) = dfs(right)
    if (right?.`val` == root.`val`) {
        rightHeight++
        length += rightHeight
    } else {
        rightHeight = 0
    }

    return Result(max(leftHeight, rightHeight), max(length, max(leftLength, rightLength)))
}

// O(n) time. O(log(n)) space. Recursive pre-order traversal.
class Solution {
    fun longestUnivaluePath(root: TreeNode?): Int {
        return dfs(root).length
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val root = case.args.root.deserialize()
            val actual = Solution().longestUnivaluePath(root)
            assertEquals(case.expected, actual)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: Int)

    @Serializable
    class Args(val root: List<Int?>)
}
