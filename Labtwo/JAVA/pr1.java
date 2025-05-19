import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexFinder {
    /**
     * Метод для поиска всех вхождений паттерна в строке
     * @param str - строка для поиска
     * @param pattern - регулярное выражение
     * @return список найденных совпадений
     */
    public static void findPattern(String str, String regex) {
        // Компилируем регулярное выражение
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        // Поиск всех совпадений
        boolean found = false;
        while (matcher.find()) {
            System.out.println("Found: " + matcher.group());
            found = true;
        }

        if (!found) {
            System.out.println("No matches found.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Стандартный паттерн для поиска (1?10+1)
        String defaultPattern = "1?10+1";
        
        System.out.println("Enter your string (pattern to search: " + defaultPattern + "):");
        String input = scanner.nextLine();

        System.out.println("Matching results:");
        findPattern(input, defaultPattern);
        
        scanner.close();
    }
}