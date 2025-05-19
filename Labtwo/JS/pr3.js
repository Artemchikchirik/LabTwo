function funcnum(numb) {
    // Проверка что все символы - цифры
    if (!/^\d+$/.test(numb)) {
        return;
    }
    
    // Проверка длины
    if (numb.length < 2) {
        return;
    }
    
    const num = parseInt(numb);
    let sum = 0;
    let proizv = 1;
    
    process.stdout.write(`${num}: `);
    
    // Сумма цифр
    for (let i = 0; i < numb.length; i++) {
        const d = parseInt(numb[i]);
        sum += d;
        process.stdout.write(numb[i]);
        if (i < numb.length - 1) {
            process.stdout.write("+");
        }
    }
    process.stdout.write(`=${sum}, `);
    
    // Произведение цифр
    for (let i = 0; i < numb.length; i++) {
        const d = parseInt(numb[i]);
        proizv *= d;
        process.stdout.write(numb[i]);
        if (i < numb.length - 1) {
            process.stdout.write("*");
        }
    }
    process.stdout.write(`=${proizv}, `);
    
    // Модуль разности
    const diff = Math.abs(sum - proizv);
    console.log(`|${sum}-${proizv}|=${diff}`);
}

const readline = require('readline').createInterface({
    input: process.stdin,
    output: process.stdout
});

readline.question('Write your number: ', input => {
    input.split(' ').forEach(numb => {
        funcnum(numb);
    });
    readline.close();
});