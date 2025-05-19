<?php
function funcnum($numb) {
    // Проверка что все символы - цифры
    if (!ctype_digit($numb)) {
        return;
    }
    
    // Проверка длины
    if (strlen($numb) < 2) {
        return;
    }
    
    $num = intval($numb);
    $sum = 0;
    $proizv = 1;
    
    echo "$num: ";
    
    // Сумма цифр
    for ($i = 0; $i < strlen($numb); $i++) {
        $d = intval($numb[$i]);
        $sum += $d;
        echo $numb[$i];
        if ($i < strlen($numb) - 1) {
            echo "+";
        }
    }
    echo "=$sum, ";
    
    // Произведение цифр
    for ($i = 0; $i < strlen($numb); $i++) {
        $d = intval($numb[$i]);
        $proizv *= $d;
        echo $numb[$i];
        if ($i < strlen($numb) - 1) {
            echo "*";
        }
    }
    echo "=$proizv, ";
    
    // Модуль разности
    $diff = abs($sum - $proizv);
    echo "|$sum-$proizv|=$diff\n";
}

echo "Write your number: ";
$input = trim(fgets(STDIN));

// Обработка каждого числа
foreach (explode(' ', $input) as $numb) {
    funcnum($numb);
}
?>