// Проверка валидности имени пользователя
function isValidUsername(username: string): boolean {
    // Проверка длины
    if (username.length < 6 || username.length > 30) {
        return false;
    }

    // Не может начинаться/заканчиваться точкой
    if (username.startsWith('.') || username.endsWith('.')) {
        return false;
    }

    // Не может содержать две точки подряд
    if (username.includes('..')) {
        return false;
    }

    // Проверка допустимых символов через регулярное выражение
    return /^[a-zA-Z0-9.*]+$/.test(username);
}

// Нормализация email
function normalizeEmail(email: string): string | null {
    // Поиск @
    const atPos = email.indexOf('@');
    if (atPos === -1) {
        return null;  // Некорректный email
    }

    // Разделение на части
    const username = email.substring(0, atPos);
    const domain = email.substring(atPos);

    let normalized = '';
    let ignoreAfterStar = false;

    // Обработка каждого символа
    for (const c of username) {
        if (c === '*') {
            ignoreAfterStar = true;
            continue;
        }

        // Добавляем символ если не точка и не после звездочки
        if (!ignoreAfterStar && c !== '.') {
            normalized += c.toLowerCase();  // В нижний регистр
        }
    }

    // Возвращаем нормализованный email
    return normalized + domain.toLowerCase();
}

// Основная программа
const input = prompt("Enter emails (separated by commas):") || "";
// Разделение ввода по запятым и очистка от пробелов
const emails = input.split(',').map(e => e.trim()).filter(e => e);

// Множество для хранения уникальных email
const uniqueEmails = new Set<string>();

// Обработка каждого email
for (const email of emails) {
    const atPos = email.indexOf('@');
    if (atPos !== -1 && isValidUsername(email.substring(0, atPos))) {
        const normalized = normalizeEmail(email);
        if (normalized) {
            uniqueEmails.add(normalized);  // Добавление в множество
        }
    }
}

// Вывод результата
console.log(`Number of unique emails: ${uniqueEmails.size}`);
