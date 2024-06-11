import sys
# sys.stdin = open("input.txt","r")

n = int(sys.stdin.readline())

dp = [[0]*n for _ in range(n)]
dp[0][0] = int(sys.stdin.readline())
# print(dp)
for i in range(1,n):
    for j,c in enumerate(map(int,sys.stdin.readline().split()),0):
        if j==0:
            dp[i][j] = dp[i-1][j]+c
        elif j == i:
            dp[i][j] = dp[i-1][j-1]+c
        else:
            dp[i][j] = max(dp[i-1][j]+c,dp[i-1][j-1]+c)

print(max(dp[-1]))