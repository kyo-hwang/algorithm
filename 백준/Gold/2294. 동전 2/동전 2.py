import sys
# sys.stdin = open("input.txt","r")

coin,money = map(int,sys.stdin.readline().split())

coins = []
for i in range(coin):
    coins.append(int(sys.stdin.readline()))
# coins.sort()

dp = [[sys.maxsize]*(money+1) for _ in range(coin+1)]

for i in range(coin+1):
    dp[i][0] = 0

for i in range(1,coin+1):
    for k in range(1,min(coins[i-1],money+1)):
        dp[i][k] = dp[i-1][k]
    for j in range(coins[i-1],money+1):
        dp[i][j] = min(dp[i-1][j],dp[i][j-coins[i-1]]+1)

# print(*dp)
result = dp[-1][money]
if result == sys.maxsize:
    print(-1)
else :
    print(result)