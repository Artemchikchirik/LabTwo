import Foundation

// Функция поиска совпадений по регулярному выражению
func findPattern(in text: String, pattern: String) -> [String] {
    guard let regex = try? NSRegularExpression(pattern: pattern) else {
        return []
    }
    
    let matches = regex.matches(in: text, range: NSRange(text.startIndex..., in: text))
    return matches.map {
        String(text[Range($0.range, in: text)!])
    }
}

print("Write your string->")
guard let input = readLine() else {
    exit(0)
}

// Регулярное выражение для поиска
let pattern = "1?10+1"

// Получаем и выводим результаты
let results = findPattern(in: input, pattern: pattern)
for res in results {
    print(res)
}