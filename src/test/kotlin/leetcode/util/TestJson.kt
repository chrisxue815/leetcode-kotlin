package leetcode.util

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.io.File

val json = Json(JsonConfiguration.Stable)

fun <T> loadTestJson(packageName: String, deserializer: DeserializationStrategy<T>): T {
    val testName = packageName.substringAfterLast('.')
    val file = File("leetcode_test_cases/$testName.json")
    val jsonText = file.readText()
    return json.parse(deserializer, jsonText)
}
