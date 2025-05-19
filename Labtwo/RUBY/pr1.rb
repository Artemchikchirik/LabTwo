# Функция поиска совпадений по регулярному выражению
def find_pattern(str, pattern)
  str.scan(pattern)
end

puts "Write your string->"
input = gets.chomp

# Регулярное выражение для поиска
pattern = /1?10+1/

# Получаем и выводим результаты
results = find_pattern(input, pattern)
results.each { |res| puts res }