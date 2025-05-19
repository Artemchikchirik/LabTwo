// Проверка валидности имени пользователя
fun isValidUsername(username: String): Boolean {
    // Проверка длины
    if (username.length < 6 || username.length > 30) {
        return false
    }

    // Не может начинаться/заканчиваться точкой
    if (username.startsWith(".") || username.endsWith(".")) {
        return false
    }

    // Не может содержать две точки подряд
    if (username.contains("..")) {
        return false
    }

    // Проверка допустимых символов
    return username.all { it.isLetterOrDigit() || it == '.' || it == '*' }
}

// Нормализация email
fun normalizeEmail(email: String): String? {
    // Поиск @
    val atPos = email.indexOf('@')
    if (atPos == -1) {
        return null  // Некорректный email
    }

    // Разделение на части
    val username = email.substring(0, atPos)
    val domain = email.substring(atPos)

    var normalized = ""
    var ignoreAfterStar = false

    // Обработка каждого символа
    for (c in username) {
        if (c == '*') {
            ignoreAfterStar = true
            continue
        }

        // Добавляем символ если не точка и не после звездочки
        if (!ignoreAfterStar && c != '.') {
            normalized += c.lowercaseChar()  // В нижний регистр
        }
    }

    // Возвращаем нормализованный email
    return normalized + domain.lowercase()
}

fun main() {
    // Ввод данных
    print("Enter emails (separated by commas): ")
    val input = readLine() ?: ""

    // Множество для хранения уникальных email
    val uniqueEmails = mutableSetOf<String>()

    // Разделение ввода по запятым
    input.split(",").forEach { email ->
        val trimmedEmail = email.trim()
        if (trimmedEmail.isNotEmpty()) {
            // Поиск @
            val atPos = trimmedEmail.indexOf('@')
            if (atPos != -1 && isValidUsername(trimmedEmail.substring(0, atPos))) {
                // Нормализация и добавление
                normalizeEmail(trimmedEmail)?.let { uniqueEmails.add(it) }
            }
        }
    }

    // Вывод результата
    println("Number of unique emails: ${uniqueEmails.size}")
}
