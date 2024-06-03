import sys
# sys.stdin = open("input.txt")

n = int(sys.stdin.readline())

dp = [1]*(n+1)

i = 2
while(i<=(n+1)):
    for j in range(i,n+1):
        dp[j] = (dp[j-i]+dp[j])%1000000000
    i*=2
    # print(dp)


print(dp[-1])