import java.util.Scanner;

public class NumberProcessor {
    /**
     * Метод для обработки числовой строки
     * @param numb - строка, содержащая число
     */
    public static void processNumber(String numb) {
        // Проверка что все символы в строке - цифры
        for (char c : numb.toCharArray()) {
            if (!Character.isDigit(c)) {
                return; // Если найден нецифровой символ, выходим из метода
            }
        }

        // Проверка что число содержит минимум 2 цифры
        if (numb.length() < 2) {
            return;
        }

        // Преобразование строки в целое число
        int num = Integer.parseInt(numb);
        int sum = 0;       // Сумма цифр
        int product = 1;   // Произведение цифр

        // Вывод исходного числа
        System.out.print(num + ": ");

        // Вычисление и вывод суммы цифр
        for (int i = 0; i < numb.length(); i++) {
            int digit = Character.getNumericValue(numb.charAt(i));
            sum += digit;
            System.out.print(digit);
            if (i < numb.length() - 1) {
                System.out.print("+"); // Вывод плюсов между цифрами
            }
        }
        System.out.print("=" + sum + ", ");

        // Вычисление и вывод произведения цифр
        for (int i = 0; i < numb.length(); i++) {
            int digit = Character.getNumericValue(numb.charAt(i));
            product *= digit;
            System.out.print(digit);
            if (i < numb.length() - 1) {
                System.out.print("*"); // Вывод звездочек между цифрами
            }
        }
        System.out.print("=" + product + ", ");

        // Вычисление и вывод модуля разности
        int diff = Math.abs(sum - product);
        System.out.println("|" + sum + "-" + product + "|=" + diff);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write your numbers separated by spaces:");
        String input = scanner.nextLine();

        // Разделение введенной строки по пробелам
        String[] numbers = input.split("\\s+");
        
        // Обработка каждого числа
        for (String num : numbers) {
            processNumber(num);
        }
        
        scanner.close();
    }
}