import sys
# sys.stdin = open("input.txt","r")

n = int(sys.stdin.readline())
stair = [int(sys.stdin.readline()) for c in range(n)]

dp = [[0,0] for _ in range(n+1)]
dp[1][0] = stair[0]

for i in range(2,n+1):
    dp[i][0] = max(dp[i-2][0],dp[i-2][1])+stair[i-1]
    dp[i][1] = dp[i-1][0]+stair[i-1]

print(max(dp[-1]))