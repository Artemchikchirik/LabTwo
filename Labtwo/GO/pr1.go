package main

import (
	"fmt"
	"regexp"
)

// Функция поиска совпадений по регулярному выражению
func findPattern(str string, pattern *regexp.Regexp) []string {
    // Ищем все совпадения в строке
    matches := pattern.FindAllString(str, -1)
    return matches
}

func main() {
    // Компилируем регулярное выражение
    pattern := regexp.MustCompile(`1?10+1`)
    
    fmt.Println("Write your string->")
    var input string
    fmt.Scanln(&input)
    
    // Получаем результаты поиска
    results := findPattern(input, pattern)
    
    // Выводим результаты
    for _, res := range results {
        fmt.Println(res)
    }
}