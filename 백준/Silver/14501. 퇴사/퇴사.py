import sys
# sys.stdin = open("input.txt","r")

n = int(sys.stdin.readline())

table = [(0,0)]

for _ in range(n):
    length, money = sys.stdin.readline().split()
    table.append((int(length),int(money)))

result = 0

def backtrack(day,money):
    global n
    global result
    if day > n:
        return
    # print(day)
    if day+1 <= n:
        backtrack(day+1,money)
    if day+table[day][0]-1 <= n:
        result = max(result,money+table[day][1])
        backtrack(day+table[day][0],money+table[day][1])

backtrack(1,0)

print(result)