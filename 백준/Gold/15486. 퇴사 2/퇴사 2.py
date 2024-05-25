import sys
# sys.stdin = open("input.txt","r")

n = int(sys.stdin.readline())

table = [(0,0)]
for _ in range(n):
    length, money = map(int,sys.stdin.readline().split())
    table.append((length,money))

dp = [0]*(n+1)

for i in range(1,n+1):
    if i+table[i][0]-1 <= n:
        dp[i+table[i][0]-1] = max(dp[i+table[i][0]-1],dp[i-1]+table[i][1])
    dp[i] = max(dp[i],dp[i-1])

print(max(dp))