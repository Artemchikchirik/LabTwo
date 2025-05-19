#include <iostream>
#include <vector>
#include <cctype>
#include <string>
#include <unordered_set>
#include <algorithm>

using namespace std;

// Функция проверки валидности имени пользователя
bool isValidUsername(const string& username) {
    // Проверка длины (6-30 символов)
    if (username.length() < 6 || username.length() > 30)
        return false;

    // Не может начинаться или заканчиваться точкой
    if (username.front() == '.' || username.back() == '.')
        return false;

    // Проверка на две точки подряд
    for (size_t i = 0; i < username.size() - 1; ++i) {
        if (username[i] == '.' && username[i+1] == '.')
            return false;
    }

    // Проверка допустимых символов (a-z, 0-9, ., *)
    for (char c : username) {
        if (!(isalnum(c) || c == '.' || c == '*'))
            return false;
    }

    return true;
}

// Функция нормализации email
string normalizeEmail(const string& email) {
    size_t at_pos = email.find('@');
    if (at_pos == string::npos) // Если нет @ - невалидный email
        return "";

    string username = email.substr(0, at_pos);
    string domain = email.substr(at_pos); // Включая @

    string normalized;
    bool ignore_after_star = false;

    // Обрабатываем имя пользователя
    for (char c : username) {
        if (c == '*') {
            ignore_after_star = true;
            continue;
        }
        
        if (!ignore_after_star && c != '.') {
            normalized += tolower(c); // Приводим к нижнему регистру
        }
    }

    // Приводим домен к нижнему регистру
    transform(domain.begin(), domain.end(), domain.begin(), ::tolower);

    return normalized + domain;
}

int main() {
    unordered_set<string> unique_emails; // Для хранения уникальных email
    string input;
    
    cout << "Enter emails (separated by commas): ";
    getline(cin, input); // Читаем всю строку

    // Обрабатываем каждый email
    size_t start = 0;
    while (true) {
        size_t end = input.find(',', start);
        string email = input.substr(start, end - start);
        
        // Удаляем пробелы в начале и конце
        email.erase(0, email.find_first_not_of(" \t"));
        email.erase(email.find_last_not_of(" \t") + 1);

        if (!email.empty()) {
            // Проверяем валидность имени пользователя
            size_t at_pos = email.find('@');
            if (at_pos != string::npos && isValidUsername(email.substr(0, at_pos))) {
                string normalized = normalizeEmail(email);
                if (!normalized.empty()) {
                    unique_emails.insert(normalized);
                }
            }
        }

        if (end == string::npos) break;
        start = end + 1;
    }

    cout << "Number of unique emails: " << unique_emails.size() << endl;
    return 0;
}
