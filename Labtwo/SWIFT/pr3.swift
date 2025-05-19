import Foundation

func funcnum(_ numb: String) {
    // Проверка что все символы - цифры
    guard CharacterSet.decimalDigits.isSuperset(of: CharacterSet(charactersIn: numb)) else {
        return
    }
    
    // Проверка длины
    guard numb.count >= 2 else {
        return
    }
    
    let num = Int(numb)!
    var sum = 0
    var proizv = 1
    
    print("\(num): ", terminator: "")
    
    // Сумма цифр
    for (i, c) in numb.enumerated() {
        let d = Int(String(c))!
        sum += d
        print(c, terminator: "")
        if i < numb.count - 1 {
            print("+", terminator: "")
        }
    }
    print("=\(sum), ", terminator: "")
    
    // Произведение цифр
    for (i, c) in numb.enumerated() {
        let d = Int(String(c))!
        proizv *= d
        print(c, terminator: "")
        if i < numb.count - 1 {
            print("*", terminator: "")
        }
    }
    print("=\(proizv), ", terminator: "")
    
    // Модуль разности
    let diff = abs(sum - proizv)
    print("|\(sum)-\(proizv)|=\(diff)")
}

print("Write your number")
if let input = readLine() {
    for numb in input.components(separatedBy: .whitespaces) {
        funcnum(numb)
    }
}