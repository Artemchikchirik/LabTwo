import Foundation

// Проверка валидности имени пользователя
func isValidUsername(_ username: String) -> Bool {
    // Проверка длины
    if username.count < 6 || username.count > 30 {
        return false
    }
    
    // Не может начинаться/заканчиваться точкой
    if username.first == "." || username.last == "." {
        return false
    }
    
    // Не может содержать две точки подряд
    if username.contains("..") {
        return false
    }
    
    // Создаем набор допустимых символов
    let validChars = CharacterSet(charactersIn: "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.*")
    // Проверяем что все символы допустимы
    return username.unicodeScalars.allSatisfy { validChars.contains($0) }
}

// Нормализация email
func normalizeEmail(_ email: String) -> String? {
    // Поиск @
    guard let atPos = email.firstIndex(of: "@") else {
        return nil  // Некорректный email
    }
    
    // Разделение на части
    let username = email[..<atPos]
    let domain = email[atPos...]
    
    var normalized = ""
    var ignoreAfterStar = false
    
    // Обработка каждого символа
    for c in username {
        if c == "*" {
            ignoreAfterStar = true
            continue
        }
        
        // Добавляем символ если не точка и не после звездочки
        if !ignoreAfterStar && c != "." {
            normalized.append(c.lowercased())  // В нижний регистр
        }
    }
    
    // Возвращаем нормализованный email
    return normalized + domain.lowercased()
}

// Основная программа
print("Enter emails (separated by commas):")
guard let input = readLine() else {
    exit(0)
}

// Множество для хранения уникальных email
var uniqueEmails = Set<String>()

// Обработка каждого email
for email in input.components(separatedBy: ",") {
    let trimmedEmail = email.trimmingCharacters(in: .whitespacesAndNewlines)
    if trimmedEmail.isEmpty {
        continue
    }
    
    // Поиск @
    if let atPos = trimmedEmail.firstIndex(of: "@") {
        let username = String(trimmedEmail[..<atPos])
        // Проверка валидности и нормализация
        if isValidUsername(username) {
            if let normalized = normalizeEmail(trimmedEmail) {
                uniqueEmails.insert(normalized)  // Добавление в множество
            }
        }
    }
}

// Вывод результата
print("Number of unique emails: \(uniqueEmails.count)")
