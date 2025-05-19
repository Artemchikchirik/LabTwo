fun funcnum(numb: String) {
    // Проверка что все символы - цифры
    if (!numb.all { it.isDigit() }) {
        return
    }
    
    // Проверка длины
    if (numb.length < 2) {
        return
    }
    
    val num = numb.toInt()
    var sum = 0
    var proizv = 1
    
    print("$num: ")
    
    // Сумма цифр
    for (i in numb.indices) {
        val d = numb[i].toString().toInt()
        sum += d
        print(numb[i])
        if (i < numb.length - 1) {
            print("+")
        }
    }
    print("=$sum, ")
    
    // Произведение цифр
    for (i in numb.indices) {
        val d = numb[i].toString().toInt()
        proizv *= d
        print(numb[i])
        if (i < numb.length - 1) {
            print("*")
        }
    }
    print("=$proizv, ")
    
    // Модуль разности
    val diff = kotlin.math.abs(sum - proizv)
    println("|$sum-$proizv|=$diff")
}

fun main() {
    println("Write your number")
    val input = readLine()!!
    
    input.split(" ").forEach { numb ->
        funcnum(numb)
    }
}