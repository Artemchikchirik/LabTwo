import re

def is_valid_username(username):
    """Проверка валидности имени пользователя"""
    # Проверка длины
    if len(username) < 6 or len(username) > 30:
        return False
    
    # Не может начинаться/заканчиваться точкой
    if username.startswith('.') or username.endswith('.'):
        return False
    
    # Не может содержать две точки подряд
    if '..' in username:
        return False
    
    # Проверка допустимых символов через регулярное выражение
    return bool(re.fullmatch(r'[a-zA-Z0-9.*]+', username))

def normalize_email(email):
    """Нормализация email"""
    # Поиск @
    at_pos = email.find('@')
    if at_pos == -1:
        return None  # Некорректный email
    
    # Разделение на части
    username = email[:at_pos]
    domain = email[at_pos:]
    
    normalized = []
    ignore_after_star = False
    
    # Обработка каждого символа
    for c in username:
        if c == '*':
            ignore_after_star = True
            continue
        
        # Добавляем символ если не точка и не после звездочки
        if not ignore_after_star and c != '.':
            normalized.append(c.lower())  # В нижний регистр
    
    # Возвращаем нормализованный email
    return ''.join(normalized) + domain.lower()

# Основная программа
emails_input = input("Enter emails (separated by commas): ")
# Разделение ввода по запятым и очистка от пробелов
emails = [email.strip() for email in emails_input.split(',') if email.strip()]

# Множество для хранения уникальных email
unique_emails = set()

# Обработка каждого email
for email in emails:
    at_pos = email.find('@')
    if at_pos != -1 and is_valid_username(email[:at_pos]):
        normalized = normalize_email(email)
        if normalized:
            unique_emails.add(normalized)  # Добавление в множество

# Вывод результата
print(f"Number of unique emails: {len(unique_emails)}")
