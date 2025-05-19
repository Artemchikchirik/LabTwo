#include <iostream> 
#include <cctype>
#include <string>
#include <cmath>
using namespace std;

// Функция для обработки числовой строки
void funcnum(const string& numb) {
    // Проверка, что все символы в строке - цифры
    for (char c : numb) {
        if (!isdigit(c)) {
            return; // Если найден нецифровой символ, выходим из функции
        }
    }

    // Если число содержит меньше 2 цифр, выходим из функции
    if (numb.size() < 2) {
        return;
    }
 
    // Преобразование строки в целое число
    int num = stoi(numb);
    int sum = 0;      // Сумма цифр
    int proizv = 1;   // Произведение цифр
    int diff = 0;     // Разность суммы и произведения

    // Вывод исходного числа
    cout << num << ": ";
    
    // Вычисление и вывод суммы цифр
    for (size_t i=0; i < numb.size(); ++i) {
        int d = numb[i] - '0'; // Преобразование символа в цифру
        sum += d;
        cout << d;
        if (i < numb.size() -1 ) {
            cout << "+"; // Вывод знака + между цифрами
        }
    }
    cout << "=" << sum << ", ";

    // Вычисление и вывод произведения цифр
    for (size_t i=0; i < numb.size(); ++i) {
        int d = numb[i] - '0';
        proizv *= d;
        cout << d;
        if (i < numb.size() -1 ) {
            cout << "*"; // Вывод знака * между цифрами
        }
    }
    cout << "=" << proizv << ", ";

    // Вывод модуля разности суммы и произведения
    cout << "|";
    diff = sum - proizv;
    if (diff >= 1) {
        cout << sum << "-" << proizv << "|=" << diff << endl;
    }
    else {
        diff *= -1; // Если разность отрицательна, берем модуль
        cout << sum << "-" << proizv << "|=" << diff << endl;
    }
}

int main() {
   string numb;
   cout << "Write your number"; // Приглашение к вводу числа

   // Чтение чисел из ввода, пока не будет нажата клавиша Enter
   while (cin >> numb) {
    funcnum(numb); // Обработка каждого введенного числа

    // Проверка на символ новой строки для выхода из цикла
    if (cin.peek() == '\n') {
        break;
    }
   }
    return 0;
}

