use regex::Regex;
use std::io;

// Функция для поиска всех вхождений регулярного выражения
fn find_pattern(text: &str, pattern: &Regex) -> Vec<String> {
    let mut result = Vec::new();
    // Ищем все совпадения в тексте
    for cap in pattern.find_iter(text) {
        result.push(cap.as_str().to_string());
    }
    result
}

fn main() {
    // Создаем регулярное выражение для поиска паттерна 1?10+1
    let pattern = Regex::new(r"1?10+1").unwrap();
    
    println!("Write your string->");
    let mut input = String::new();
    io::stdin().read_line(&mut input).expect("Ошибка чтения строки");
    
    // Удаляем символы перевода строки
    let input = input.trim();
    
    // Получаем результаты поиска
    let results = find_pattern(input, &pattern);
    
    // Выводим все найденные совпадения
    for res in results {
        println!("{}", res);
    }
}