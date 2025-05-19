package main

import (
	"fmt"
	"strconv"
	"strings"
)

func funcnum(numb string) {
	// Проверка что все символы - цифры
	for _, c := range numb {
		if !(c >= '0' && c <= '9') {
			return
		}
	}

	// Проверка длины
	if len(numb) < 2 {
		return
	}

	// Парсинг числа
	num, _ := strconv.Atoi(numb)
	sum := 0
	proizv := 1

	// Вывод числа
	fmt.Printf("%d: ", num)

	// Вычисление суммы
	for i, c := range numb {
		d := int(c - '0')
		sum += d
		fmt.Printf("%c", c)
		if i < len(numb)-1 {
			fmt.Print("+")
		}
	}
	fmt.Printf("=%d, ", sum)

	// Вычисление произведения
	for i, c := range numb {
		d := int(c - '0')
		proizv *= d
		fmt.Printf("%c", c)
		if i < len(numb)-1 {
			fmt.Print("*")
		}
	}
	fmt.Printf("=%d, ", proizv)

	// Модуль разности
	diff := sum - proizv
	if diff < 0 {
		diff = -diff
	}
	fmt.Printf("|%d-%d|=%d\n", sum, proizv, diff)
}

func main() {
	fmt.Println("Write your number")
	var input string
	fmt.Scanln(&input)

	// Разделение ввода по пробелам
	for _, numb := range strings.Fields(input) {
		funcnum(numb)
	}
}