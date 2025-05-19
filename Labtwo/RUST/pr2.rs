// Импорт необходимых модулей
use std::collections::HashSet;  // Для хранения уникальных email
use std::ascii::AsciiExt;       // Для работы с ASCII символами

// Функция проверки валидности имени пользователя
fn is_valid_username(username: &str) -> bool {
    // Проверка длины имени (6-30 символов)
    if username.len() < 6 || username.len() > 30 {
        return false;
    }
    
    // Не может начинаться или заканчиваться точкой
    if username.starts_with('.') || username.ends_with('.') {
        return false;
    }
    
    // Не может содержать две точки подряд
    if username.contains("..") {
        return false;
    }
    
    // Допустимые символы: буквы, цифры, точка, звездочка
    username.chars().all(|c| c.is_ascii_alphanumeric() || c == '.' || c == '*')
}

// Функция нормализации email
fn normalize_email(email: &str) -> Option<String> {
    // Поиск символа @
    let at_pos = email.find('@')?;  // Возвращает None если @ не найден
    
    // Разделение на имя пользователя и домен
    let (username, domain) = email.split_at(at_pos);
    
    let mut normalized = String::new();  // Нормализованная строка
    let mut ignore_after_star = false;   // Флаг игнорирования после звездочки
    
    // Обработка каждого символа в имени пользователя
    for c in username.chars() {
        if c == '*' {
            ignore_after_star = true;  // Включаем флаг игнорирования
            continue;
        }
        
        // Добавляем символ если не точка и не после звездочки
        if !ignore_after_star && c != '.' {
            normalized.push(c.to_ascii_lowercase());  // Приводим к нижнему регистру
        }
    }
    
    // Приводим домен к нижнему регистру
    let normalized_domain = domain.to_ascii_lowercase();
    Some(normalized + &normalized_domain)  // Возвращаем нормализованный email
}

fn main() {
    // Ввод данных от пользователя
    println!("Enter emails (separated by commas):");
    let mut input = String::new();
    std::io::stdin().read_line(&mut input).unwrap();
    
    // Хранилище уникальных email
    let mut unique_emails = HashSet::new();
    
    // Обработка каждого email
    for email in input.split(',') {
        let email = email.trim();  // Удаляем пробелы
        if email.is_empty() {
            continue;  // Пропускаем пустые строки
        }
        
        // Проверяем наличие @
        if let Some(at_pos) = email.find('@') {
            let username = &email[..at_pos];
            // Проверяем валидность имени пользователя
            if is_valid_username(username) {
                // Нормализуем email
                if let Some(normalized) = normalize_email(email) {
                    unique_emails.insert(normalized);  // Добавляем в множество
                }
            }
        }
    }
    
    // Вывод результата
    println!("Number of unique emails: {}", unique_emails.len());
}
