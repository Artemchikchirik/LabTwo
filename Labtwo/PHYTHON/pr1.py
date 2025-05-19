import re

# Функция поиска совпадений по регулярному выражению
def find_pattern(text, pattern):
    # Ищем все совпадения в тексте
    return re.findall(pattern, text)

print("Write your string->")
input_str = input().strip()

# Регулярное выражение для поиска
pattern = r'1?10+1'

# Получаем и выводим результаты
results = find_pattern(input_str, pattern)
for res in results:
    print(res)