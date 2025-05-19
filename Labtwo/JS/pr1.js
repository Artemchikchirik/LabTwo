// Функция поиска совпадений по регулярному выражению
function findPattern(str, pattern) {
    let matches = [];
    // Используем метод matchAll для поиска всех совпадений
    let match;
    while ((match = pattern.exec(str)) !== null) {
        matches.push(match[0]);
    }
    return matches;
}

console.log("Write your string->");
const readline = require('readline').createInterface({
    input: process.stdin,
    output: process.stdout
});

readline.question('', input => {
    // Регулярное выражение для поиска
    const pattern = /1?10+1/g;
    
    // Получаем и выводим результаты
    const results = findPattern(input, pattern);
    results.forEach(res => console.log(res));
    
    readline.close();
});