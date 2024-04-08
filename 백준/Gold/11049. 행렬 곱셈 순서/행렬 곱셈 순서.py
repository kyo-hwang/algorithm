import sys

n = int(sys.stdin.readline())
m = [[int(c) for c in sys.stdin.readline().split()] for _ in range(n)]

dp = [[0]*n for _ in range(n)]

for i in range(1,n):
    for j in range(0,n-i):
        curMin = sys.maxsize
        mul = m[j+i][1]*m[j][0]
        for k in range(j,j+i):
            val = dp[j][k]+dp[k+1][j+i]+m[k][1]*mul
            if val < curMin:
                curMin = val
        dp[j][j+i] = curMin

print(dp[0][-1])