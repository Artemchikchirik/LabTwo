def funcnum(numb)
  # Проверка что все символы - цифры
  return unless numb =~ /^\d+$/
  
  # Проверка длины
  return if numb.length < 2
  
  num = numb.to_i
  sum = 0
  proizv = 1
  
  print "#{num}: "
  
  # Сумма цифр
  numb.chars.each_with_index do |c, i|
    d = c.to_i
    sum += d
    print c
    print "+" if i < numb.length - 1
  end
  print "=#{sum}, "
  
  # Произведение цифр
  numb.chars.each_with_index do |c, i|
    d = c.to_i
    proizv *= d
    print c
    print "*" if i < numb.length - 1
  end
  print "=#{proizv}, "
  
  # Модуль разности
  diff = (sum - proizv).abs
  puts "|#{sum}-#{proizv}|=#{diff}"
end

puts "Write your number"
input = gets.chomp
input.split.each { |numb| funcnum(numb) }