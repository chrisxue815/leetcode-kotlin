package leetcode.util

import java.util.ArrayDeque
import java.util.Optional
import java.util.stream.Stream
import kotlin.test.Test
import kotlin.test.assertEquals

class TreeNode(var `val`: Int = 0) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

fun TreeNode?.serialize(): ArrayList<Int?> {
    val result = ArrayList<Int?>()
    val queue = ArrayDeque<Optional<TreeNode>>()
    queue.add(Optional.ofNullable(this))

    while (queue.isNotEmpty()) {
        val curr = queue.pollFirst().orElse(null)

        if (curr == null) {
            result.add(null)
        } else {
            result.add(curr.`val`)
            queue.offerLast(Optional.ofNullable(curr.left))
            queue.offerLast(Optional.ofNullable(curr.right))
        }
    }

    while (result.isNotEmpty() && result.last() == null) {
        result.removeAt(result.size - 1)
    }

    return result
}

fun Iterable<Int?>.deserialize(): TreeNode? {
    val dummy = TreeNode()
    val queue = ArrayDeque<Pair<TreeNode, Int>>()
    queue.add(Pair(dummy, 0))

    for (value in this) {
        val (curr, pos) = queue.pollFirst()

        val child = if (value != null) TreeNode(value) else null

        if (pos == 0) {
            curr.left = child
        } else {
            curr.right = child
        }

        if (child != null) {
            queue.add(Pair(child, 0))
            queue.add(Pair(child, 1))
        }
    }

    return dummy.left
}

class TreeNodeTest {
    @Test
    fun test() {
        val testCases = Stream.of(
            (arrayListOf(1, 2, 3, 4, 5, 6, 7)),
            (ArrayList()),
            (arrayListOf(1)),
            (arrayListOf(1, null, 2, null, 3)),
            (arrayListOf(1, 2, 3, 4, null, null, 5)),
            (arrayListOf(1, 2, 3, null, 4, 5))
        )

        for (case in testCases) {
            val actual = case.deserialize().serialize()
            assertEquals(case, actual)
        }
    }
}
