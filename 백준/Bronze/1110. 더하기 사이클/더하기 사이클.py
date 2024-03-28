num = int(input())
result = num

cycle = 0

while True:
    cycle +=1
    if num >= 10 :
        num = (num//10 + num%10)%10+(num%10)*10
    else :
        num = num*10+num


    if num == result:
        break


print(cycle)