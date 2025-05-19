using System;

class Program
{
    static void Funcnum(string numb)
    {
        // Проверка что все символы - цифры
        foreach (char c in numb)
        {
            if (!char.IsDigit(c))
                return;
        }

        // Проверка длины
        if (numb.Length < 2)
            return;

        int num = int.Parse(numb);
        int sum = 0;
        int proizv = 1;

        Console.Write($"{num}: ");

        // Сумма цифр
        for (int i = 0; i < numb.Length; i++)
        {
            int d = numb[i] - '0';
            sum += d;
            Console.Write(numb[i]);
            if (i < numb.Length - 1)
                Console.Write("+");
        }
        Console.Write($"={sum}, ");

        // Произведение цифр
        for (int i = 0; i < numb.Length; i++)
        {
            int d = numb[i] - '0';
            proizv *= d;
            Console.Write(numb[i]);
            if (i < numb.Length - 1)
                Console.Write("*");
        }
        Console.Write($"={proizv}, ");

        // Модуль разности
        int diff = Math.Abs(sum - proizv);
        Console.WriteLine($"|{sum}-{proizv}|={diff}");
    }

    static void Main()
    {
        Console.WriteLine("Write your number");
        string input = Console.ReadLine();
        
        foreach (string numb in input.Split(' '))
        {
            Funcnum(numb);
        }
    }
}