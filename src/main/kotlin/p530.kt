package leetcode.p530

import leetcode.util.TreeNode
import leetcode.util.deserialize
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


// O(n) time. O(log(n)) space. Recursive in-order traversal.
class Solution {
    fun getMinimumDifference(root: TreeNode?): Int {
        var minDiff = Int.MAX_VALUE
        var prev = -1

        tailrec fun dfs(root: TreeNode?) {
            if (root == null) {
                return
            }

            @Suppress("NON_TAIL_RECURSIVE_CALL")
            dfs(root.left)

            if (prev != -1) {
                minDiff = Math.min(minDiff, root.`val` - prev)
            }

            prev = root.`val`

            dfs(root.right)
        }

        dfs(root)

        return minDiff
    }
}

class SolutionTest {
    @ParameterizedTest
    @MethodSource("createTestData")
    fun test(rootList: List<Int>, expected: Int) {
        val root = rootList.deserialize()
        val actual = Solution().getMinimumDifference(root)
        assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        fun createTestData(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of(listOf(1, null, 3, 2), 1)
            )
        }
    }
}
