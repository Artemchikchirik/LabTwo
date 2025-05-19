use std::io;

// Функция обработки числовой строки
fn funcnum(numb: &str) {
    // Проверка что все символы - цифры
    if !numb.chars().all(|c| c.is_ascii_digit()) {
        return;
    }
    
    // Проверка длины числа
    if numb.len() < 2 {
        return;
    }
    
    // Парсинг числа
    let num: i32 = numb.parse().unwrap();
    let mut sum = 0;
    let mut proizv = 1;
    
    // Вычисление суммы и произведения
    let digits: Vec<char> = numb.chars().collect();
    print!("{}: ", num);
    
    // Сумма цифр
    for (i, c) in digits.iter().enumerate() {
        let d = c.to_digit(10).unwrap() as i32;
        sum += d;
        print!("{}", d);
        if i < digits.len() - 1 {
            print!("+");
        }
    }
    print!("={}, ", sum);
    
    // Произведение цифр
    for (i, c) in digits.iter().enumerate() {
        let d = c.to_digit(10).unwrap() as i32;
        proizv *= d;
        print!("{}", d);
        if i < digits.len() - 1 {
            print!("*");
        }
    }
    print!("={}, ", proizv);
    
    // Модуль разности
    let diff = (sum - proizv).abs();
    println!("|{}-{}|={}", sum, proizv, diff);
}

fn main() {
    println!("Write your number");
    let mut input = String::new();
    io::stdin().read_line(&mut input).unwrap();
    
    // Обработка каждого числа в строке
    for numb in input.split_whitespace() {
        funcnum(numb);
    }
}