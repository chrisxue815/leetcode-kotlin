package leetcode.util

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.Json
import java.io.File

fun <T> loadTestJson(packageName: String, deserializer: DeserializationStrategy<T>): T {
    val testName = packageName.substringAfterLast('.')
    val file = File("leetcode_test_cases/$testName.json")
    val jsonText = file.readText()
    return Json.decodeFromString(deserializer, jsonText)
}
