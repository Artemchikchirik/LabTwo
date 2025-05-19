import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class EmailValidator {
    /**
     * Проверка валидности имени пользователя
     * @param username - имя пользователя для проверки
     * @return true если имя валидно, иначе false
     */
    public static boolean isValidUsername(String username) {
        // Длина имени должна быть от 6 до 30 символов
        if (username.length() < 6 || username.length() > 30) {
            return false;
        }

        // Не может начинаться или заканчиваться точкой
        if (username.startsWith(".") || username.endsWith(".")) {
            return false;
        }

        // Не может содержать две точки подряд
        if (username.contains("..")) {
            return false;
        }

        // Может содержать только буквы, цифры, точки и звездочки
        for (char c : username.toCharArray()) {
            if (!(Character.isLetterOrDigit(c) || c == '.' || c == '*')) {
                return false;
            }
        }

        return true;
    }

    /**
     * Нормализация email адреса
     * @param email - email для нормализации
     * @return нормализованный email или null если email невалиден
     */
    public static String normalizeEmail(String email) {
        int atIndex = email.indexOf('@');
        if (atIndex == -1) {
            return null; // Некорректный email (нет @)
        }

        String username = email.substring(0, atIndex);
        String domain = email.substring(atIndex).toLowerCase(); // Домен в нижний регистр

        // Проверяем валидность имени пользователя
        if (!isValidUsername(username)) {
            return null;
        }

        StringBuilder normalized = new StringBuilder();
        boolean ignoreAfterStar = false;

        // Обрабатываем имя пользователя
        for (char c : username.toCharArray()) {
            if (c == '*') {
                ignoreAfterStar = true;
                continue;
            }

            if (!ignoreAfterStar && c != '.') {
                normalized.append(Character.toLowerCase(c));
            }
        }

        // Возвращаем нормализованный email
        return normalized.toString() + domain;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<String> uniqueEmails = new HashSet<>();

        System.out.println("Enter emails separated by commas:");
        String input = scanner.nextLine();

        // Разделяем ввод по запятым
        String[] emails = input.split(",");

        for (String email : emails) {
            String trimmedEmail = email.trim();
            if (!trimmedEmail.isEmpty()) {
                String normalized = normalizeEmail(trimmedEmail);
                if (normalized != null) {
                    uniqueEmails.add(normalized);
                }
            }
        }

        System.out.println("Number of unique valid emails: " + uniqueEmails.size());
        scanner.close();
    }
}