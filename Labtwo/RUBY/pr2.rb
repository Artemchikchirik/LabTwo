def is_valid_username(username)
  # Проверка длины
  return false if username.length < 6 || username.length > 30
  # Не может начинаться/заканчиваться точкой
  return false if username.start_with?('.') || username.end_with?('.')
  # Не может содержать две точки подряд
  return false if username.include?('..')
  # Проверка допустимых символов через регулярное выражение
  username.match?(/^[a-zA-Z0-9.*]+$/)
end

def normalize_email(email)
  # Поиск @
  at_pos = email.index('@')
  return nil unless at_pos

  # Разделение на части
  username = email[0...at_pos]
  domain = email[at_pos..-1]

  normalized = ''
  ignore_after_star = false

  # Обработка каждого символа
  username.each_char do |c|
    if c == '*'
      ignore_after_star = true
      next
    end

    # Добавляем символ если не точка и не после звездочки
    normalized += c.downcase unless ignore_after_star || c == '.'
  end

  # Возвращаем нормализованный email
  normalized + domain.downcase
end

# Основная программа
print "Enter emails (separated by commas): "
input = gets.chomp

# Множество для хранения уникальных email
unique_emails = Set.new

# Разделение ввода по запятым
input.split(',').each do |email|
  email = email.strip  # Удаление пробелов
  next if email.empty?

  # Поиск @
  at_pos = email.index('@')
  next unless at_pos

  # Проверка валидности и нормализация
  username = email[0...at_pos]
  if is_valid_username(username)
    normalized = normalize_email(email)
    unique_emails.add(normalized) if normalized  # Добавление в множество
  end
end

# Вывод результата
puts "Number of unique emails: #{unique_emails.size}"
