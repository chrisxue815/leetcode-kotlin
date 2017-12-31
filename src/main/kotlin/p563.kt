package leetcode.p563

import leetcode.util.TreeNode
import leetcode.util.deserialize
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

// O(n) time. O(log(n)) space. Recursive post-order traversal.
class Solution {
    fun findTilt(root: TreeNode?): Int {
        var tilt = 0

        fun dfs(root: TreeNode?): Int {
            if (root == null) {
                return 0
            }

            val leftSum = dfs(root.left)
            val rightSum = dfs(root.right)
            tilt += Math.abs(leftSum - rightSum)
            return root.`val` + leftSum + rightSum
        }

        dfs(root)

        return tilt
    }
}

class SolutionTest {
    @ParameterizedTest
    @MethodSource("createTestData")
    fun test(root: List<Int?>, expected: Int) {
        val root = root.deserialize()
        val actual = Solution().findTilt(root)
        assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        fun createTestData(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of(listOf(1, 2, 3), 1)
            )
        }
    }
}
