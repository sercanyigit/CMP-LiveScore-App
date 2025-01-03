actual class JsonReader {
    actual fun readJsonFromResources(fileName: String): String {
        return try {
            val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)
                ?: throw IllegalStateException("Resource not found")
            
            inputStream.bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            throw IllegalStateException("Error reading JSON file: ${e.message}")
        }
    }
} 