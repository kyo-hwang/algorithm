import sys
from collections import deque
# sys.stdin = open("input.txt","r")

n = int(sys.stdin.readline())
board = [[int(c) for c in sys.stdin.readline().split()] for _ in range(n)]

dp = [[0]*n for _ in range(n)]
dp[0][0] = 1

for i in range(n):
    for j in range(n):
        # print(i,j)
        if board[i][j] != 0:
            right = j+board[i][j]
            down = i+ board[i][j]
            if right < n:
                dp[i][right] += dp[i][j]
            if down < n :
                dp[down][j] += dp[i][j]



print(dp[-1][-1])