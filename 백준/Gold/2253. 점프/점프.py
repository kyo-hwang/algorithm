import sys

# sys.stdin = open("input.txt","r")

n,m = map(int,sys.stdin.readline().split())

tooSmall = [False]*(n+1)

for _ in range(m):
    tooSmall[int(sys.stdin.readline())] = True

dp = [[20000]*201 for _ in range(n+1)]
dp[2][1] = 1

for i in range(2,n+1):
    if not tooSmall[i]:
        for j in range(1,201):
            if dp[i][j] != 20000:
                for k in range(-1,2):
                    if i+j+k < (n+1) and j+k >= 1 and not tooSmall[i+j+k]:
                        dp[i+j+k][j+k] = min(dp[i+j+k][j+k],dp[i][j]+1)
result = min(dp[-1])

if result == 20000:
    print(-1)
else:
    print(result)
