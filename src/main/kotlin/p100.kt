package leetcode.p100

import leetcode.util.TreeNode
import leetcode.util.deserialize
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

// O(n) time. O(log(n)) space. Recursive pre-order traversal.
class Solution {
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null || q == null) {
            return p === q
        }

        return p.`val` == q.`val` && isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
    }
}

class SolutionTest {
    @ParameterizedTest
    @MethodSource("createTestData")
    fun test(p: List<Int?>, q: List<Int?>, expected: Boolean) {
        val p = p.deserialize()
        val q = q.deserialize()
        val actual = Solution().isSameTree(p, q)
        assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        fun createTestData(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of(listOf(1, 2, 3), listOf(1, 2, 3), true),
                    Arguments.of(listOf(1, 2), listOf(1, null, 2), false),
                    Arguments.of(listOf(1, 2, 1), listOf(1, 1, 2), false)
            )
        }
    }
}
