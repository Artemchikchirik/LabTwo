package main

import (
	"fmt"
	"strings"
	"unicode"
)

// Проверка валидности имени пользователя
func isValidUsername(username string) bool {
	// Проверка длины
	if len(username) < 6 || len(username) > 30 {
		return false
	}

	// Не может начинаться/заканчиваться точкой
	if strings.HasPrefix(username, ".") || strings.HasSuffix(username, ".") {
		return false
	}

	// Не может содержать две точки подряд
	if strings.Contains(username, "..") {
		return false
	}

	// Проверка допустимых символов
	for _, c := range username {
		if !(unicode.IsLetter(c) || unicode.IsDigit(c) && c != '.' && c != '*' {
			return false
		}
	}

	return true
}

// Нормализация email
func normalizeEmail(email string) string {
	// Поиск разделителя @
	atPos := strings.Index(email, "@")
	if atPos == -1 {
		return ""  // Некорректный email
	}

	// Разделение на части
	username := email[:atPos]
	domain := email[atPos:]

	var normalized strings.Builder  // Для эффективной конкатенации
	ignoreAfterStar := false       // Флаг игнорирования

	// Обработка имени пользователя
	for _, c := range username {
		if c == '*' {
			ignoreAfterStar = true
			continue
		}

		if !ignoreAfterStar && c != '.' {
			normalized.WriteRune(unicode.ToLower(c))  // В нижний регистр
		}
	}

	// Добавляем домен в нижнем регистре
	normalized.WriteString(strings.ToLower(domain))
	return normalized.String()
}

func main() {
	// Ввод данных
	fmt.Println("Enter emails (separated by commas):")
	var input string
	fmt.Scanln(&input)

	// Хранилище уникальных email (map как множество)
	uniqueEmails := make(map[string]struct{})

	// Разделение ввода по запятым
	for _, email := range strings.Split(input, ",") {
		email = strings.TrimSpace(email)  // Обрезка пробелов
		if email == "" {
			continue
		}

		// Поиск @
		atPos := strings.Index(email, "@")
		if atPos != -1 && isValidUsername(email[:atPos]) {
			normalized := normalizeEmail(email)
			if normalized != "" {
				uniqueEmails[normalized] = struct{}{}  // Добавление в множество
			}
		}
	}

	// Вывод количества уникальных email
	fmt.Printf("Number of unique emails: %d\n", len(uniqueEmails))
}
