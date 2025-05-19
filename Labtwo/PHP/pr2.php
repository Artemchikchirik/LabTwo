<?php

// Проверка валидности имени пользователя
function isValidUsername($username) {
    // Проверка длины
    if (strlen($username) < 6 || strlen($username) > 30) {
        return false;
    }

    // Не может начинаться/заканчиваться точкой
    if ($username[0] === '.' || substr($username, -1) === '.') {
        return false;
    }

    // Не может содержать две точки подряд
    if (strpos($username, '..') !== false) {
        return false;
    }

    // Проверка допустимых символов через регулярное выражение
    return preg_match('/^[a-zA-Z0-9.*]+$/', $username) === 1;
}

// Нормализация email
function normalizeEmail($email) {
    // Поиск символа @
    $at_pos = strpos($email, '@');
    if ($at_pos === false) {
        return '';  // Некорректный email
    }

    // Разделение на части
    $username = substr($email, 0, $at_pos);
    $domain = substr($email, $at_pos);

    $normalized = '';
    $ignore_after_star = false;

    // Обработка каждого символа
    for ($i = 0; $i < strlen($username); $i++) {
        $c = $username[$i];
        if ($c === '*') {
            $ignore_after_star = true;
            continue;
        }

        // Добавляем символ если не точка и не после звездочки
        if (!$ignore_after_star && $c !== '.') {
            $normalized .= strtolower($c);  // В нижний регистр
        }
    }

    // Возвращаем нормализованный email
    return $normalized . strtolower($domain);
}

// Основная программа
echo "Enter emails (separated by commas): ";
$input = trim(fgets(STDIN));  // Чтение ввода

$unique_emails = array();  // Массив для уникальных email

// Разделение ввода по запятым
foreach (explode(',', $input) as $email) {
    $email = trim($email);  // Обрезка пробелов
    if (empty($email)) {
        continue;  // Пропуск пустых
    }

    // Поиск @
    $at_pos = strpos($email, '@');
    if ($at_pos !== false && isValidUsername(substr($email, 0, $at_pos))) {
        $normalized = normalizeEmail($email);
        if (!empty($normalized)) {
            $unique_emails[$normalized] = true;  // Используем ключи для уникальности
        }
    }
}

// Вывод результата
echo "Number of unique emails: " . count($unique_emails) . "\n";
?>
