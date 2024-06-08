import sys
# sys.stdin = open("input.txt","r")

n = int(sys.stdin.readline())
dp = [[0]*10 for _ in range(n+1)]

for i in range(10):
    dp[1][i] = 10-i

for i in range(2,n+1):
    for j in range(9,-1,-1):
        if j == 9:
            dp[i][j] = 1
        else:
            dp[i][j] = (dp[i][j+1]+dp[i-1][j])%10007

print(dp[-1][0])