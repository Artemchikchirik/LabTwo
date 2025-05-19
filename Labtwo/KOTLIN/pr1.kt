import java.util.regex.Pattern

// Функция поиска совпадений по регулярному выражению
fun findPattern(input: String, pattern: Pattern): List<String> {
    val matcher = pattern.matcher(input)
    val results = mutableListOf<String>()
    // Ищем все совпадения
    while (matcher.find()) {
        results.add(matcher.group())
    }
    return results
}

fun main() {
    // Компилируем регулярное выражение
    val pattern = Pattern.compile("1?10+1")
    
    println("Write your string->")
    val input = readLine() ?: ""
    
    // Получаем и выводим результаты
    val results = findPattern(input, pattern)
    results.forEach { println(it) }
}