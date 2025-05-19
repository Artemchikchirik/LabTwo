#include <iostream>
#include <string>
#include <regex>
#include <list>
using namespace std;

// Функция для поиска всех вхождений регулярного выражения в строке
vector <string> find_pattern( const string& str, const regex& pattern ) {
    vector <string> result; // Вектор для хранения найденных совпадений

    // Создаем итератор для поиска всех вхождений регулярного выражения в строке
    sregex_iterator sreg(str.begin(), str.end(), pattern );
    sregex_iterator end; // Конечный итератор

    // Перебираем все найденные совпадения
    while (sreg != end) {
        result.push_back(sreg->str()); // Добавляем найденное совпадение в вектор
        ++sreg; // Переходим к следующему совпадению
    }
    return result; // Возвращаем вектор с результатами
}

int main () {
    string str;
    cout << "Write your string->";
    getline(cin, str); // Считываем строку от пользователя

    // Регулярное выражение для поиска паттернов:
    regex pattern(R"(1?10+1)");

    vector <string> final_result;
    final_result = find_pattern(str, pattern); // Ищем все совпадения с паттерном
    
    // Выводим все найденные совпадения
    for (const auto& aaa : final_result){
        cout << aaa << endl;
    }
    
    return 0;
}
