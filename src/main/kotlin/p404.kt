package leetcode.p404

import leetcode.util.TreeNode
import leetcode.util.deserialize
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

// O(n) time. O(log(n)) space. Recursive pre-order traversal.
class Solution {
    fun sumOfLeftLeaves(root: TreeNode?): Int {
        var sum = 0

        tailrec fun dfs(root: TreeNode?) {
            if (root == null) {
                return
            }

            val left = root.left

            if (left != null && left.left == null && left.right == null) {
                sum += left.`val`
            } else {
                dfs(left)
            }

            dfs(root.right)
        }

        dfs(root)

        return sum
    }
}

class SolutionTest {
    @ParameterizedTest
    @MethodSource("createTestData")
    fun test(root: ArrayList<Int?>, expected: Int) {
        val root = root.deserialize()
        val actual = Solution().sumOfLeftLeaves(root)
        assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        fun createTestData(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of(arrayListOf(3, 9, 20, null, null, 15, 7), 24)
            )
        }
    }
}
