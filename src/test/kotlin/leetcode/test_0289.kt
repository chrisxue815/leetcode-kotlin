@file:Suppress("PackageDirectoryMismatch")

package leetcode.test_0289

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import leetcode.util.loadTestJson
import kotlin.test.Test
import kotlin.test.assertEquals

val NEIGHBORS = arrayOf(
    arrayOf(-1, -1),
    arrayOf(-1, 0),
    arrayOf(-1, 1),
    arrayOf(0, -1),
    arrayOf(0, 1),
    arrayOf(1, -1),
    arrayOf(1, 0),
    arrayOf(1, 1)
)

// O(n) time. O(1) space. Array, bit manipulation.
class Solution {
    fun gameOfLife(board: Array<IntArray>) {
        for (i in board.indices) {
            val row = board[i]
            for (j in row.indices) {
                val liveNeighbors = NEIGHBORS.sumBy { (di, dj) ->  // delta i and delta j
                    val ni = i + di  // neighbor i
                    val nj = j + dj  // neighbor j
                    if (ni >= 0 && ni < board.size && nj >= 0 && nj < row.size) {
                        board[ni][nj] and 1
                    } else {
                        0
                    }
                }

                val currState = row[j]
                if (liveNeighbors == 3 || liveNeighbors == 2 && (currState and 1) == 1) {
                    row[j] = currState or 0b10
                }
            }
        }

        for (row in board) {
            for (j in row.indices) {
                row[j] = row[j] shr 1
            }
        }
    }
}

class SolutionTest {
    @Test
    fun test() {
        val testData = loadTestJson(javaClass.packageName, TestJson.serializer())

        for (case in testData.test_cases) {
            val msg = Json.encodeToString(Args.serializer(), case.args)
            Solution().gameOfLife(case.args.board)
            val actual = case.args.board.map { it.asList() }.toList()
            assertEquals(case.expected, actual, msg)
        }
    }

    @Serializable
    class TestJson(val test_cases: List<TestCase>)

    @Serializable
    class TestCase(val args: Args, val expected: List<List<Int>>)

    @Serializable
    class Args(val board: Array<IntArray>)
}
