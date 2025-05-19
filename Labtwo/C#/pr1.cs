using System;
using System.Text.RegularExpressions;
using System.Collections.Generic;

class Program
{
    // Функция поиска совпадений по регулярному выражению
    static List<string> FindPattern(string input, Regex pattern)
    {
        List<string> results = new List<string>();
        // Ищем все совпадения
        foreach (Match match in pattern.Matches(input))
        {
            results.Add(match.Value);
        }
        return results;
    }

    static void Main()
    {
        // Компилируем регулярное выражение
        Regex pattern = new Regex(@"1?10+1");
        
        Console.WriteLine("Write your string->");
        string input = Console.ReadLine();
        
        // Получаем и выводим результаты
        List<string> results = FindPattern(input, pattern);
        foreach (string res in results)
        {
            Console.WriteLine(res);
        }
    }
}