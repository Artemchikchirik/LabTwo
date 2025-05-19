using System;
using System.Collections.Generic;
using System.Linq;

class Program
{
    // Проверка валидности имени пользователя
    static bool IsValidUsername(string username)
    {
        // Проверка длины
        if (username.Length < 6 || username.Length > 30)
            return false;

        // Не может начинаться/заканчиваться точкой
        if (username.StartsWith(".") || username.EndsWith("."))
            return false;

        // Не может содержать две точки подряд
        if (username.Contains(".."))
            return false;

        // Проверка допустимых символов
        return username.All(c => char.IsLetterOrDigit(c) || c == '.' || c == '*');
    }

    // Нормализация email
    static string NormalizeEmail(string email)
    {
        // Поиск @
        int atPos = email.IndexOf('@');
        if (atPos == -1)
            return null;  // Некорректный email

        // Разделение на части
        string username = email.Substring(0, atPos);
        string domain = email.Substring(atPos);

        string normalized = "";
        bool ignoreAfterStar = false;

        // Обработка каждого символа
        foreach (char c in username)
        {
            if (c == '*')
            {
                ignoreAfterStar = true;
                continue;
            }

            // Добавляем символ если не точка и не после звездочки
            if (!ignoreAfterStar && c != '.')
            {
                normalized += char.ToLower(c);  // В нижний регистр
            }
        }

        // Возвращаем нормализованный email
        return normalized + domain.ToLower();
    }

    static void Main()
    {
        // Ввод данных
        Console.WriteLine("Enter emails (separated by commas):");
        string input = Console.ReadLine();

        // Множество для хранения уникальных email
        HashSet<string> uniqueEmails = new HashSet<string>();

        // Разделение ввода по запятым
        foreach (string email in input.Split(','))
        {
            string trimmedEmail = email.Trim();
            if (string.IsNullOrEmpty(trimmedEmail))
                continue;

            // Поиск @
            int atPos = trimmedEmail.IndexOf('@');
            if (atPos != -1 && IsValidUsername(trimmedEmail.Substring(0, atPos)))
            {
                string normalized = NormalizeEmail(trimmedEmail);
                if (normalized != null)
                {
                    uniqueEmails.Add(normalized);  // Добавление в множество
                }
            }
        }

        // Вывод результата
        Console.WriteLine($"Number of unique emails: {uniqueEmails.Count}");
    }
}
