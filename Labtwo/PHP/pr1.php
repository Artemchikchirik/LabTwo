<?php
// Функция для поиска совпадений по регулярному выражению
function find_pattern($str, $pattern) {
    $matches = array();
    // Ищем все совпадения в строке
    preg_match_all($pattern, $str, $matches);
    return $matches[0]; // Возвращаем только полные совпадения
}

echo "Write your string->";
$input = trim(fgets(STDIN)); // Читаем ввод пользователя

// Регулярное выражение для поиска паттерна
$pattern = '/1?10+1/';

// Получаем результаты поиска
$results = find_pattern($input, $pattern);

// Выводим результаты
foreach ($results as $res) {
    echo $res . PHP_EOL;
}
?>