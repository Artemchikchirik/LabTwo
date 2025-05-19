def funcnum(numb):
    # Проверка что все символы - цифры
    if not numb.isdigit():
        return
    
    # Проверка длины
    if len(numb) < 2:
        return
    
    num = int(numb)
    sum_d = 0
    product = 1
    
    print(f"{num}: ", end="")
    
    # Сумма цифр
    for i, c in enumerate(numb):
        d = int(c)
        sum_d += d
        print(c, end="")
        if i < len(numb) - 1:
            print("+", end="")
    print(f"={sum_d}, ", end="")
    
    # Произведение цифр
    for i, c in enumerate(numb):
        d = int(c)
        product *= d
        print(c, end="")
        if i < len(numb) - 1:
            print("*", end="")
    print(f"={product}, ", end="")
    
    # Модуль разности
    diff = abs(sum_d - product)
    print(f"|{sum_d}-{product}|={diff}")

print("Write your number")
input_str = input().strip()
for numb in input_str.split():
    funcnum(numb)