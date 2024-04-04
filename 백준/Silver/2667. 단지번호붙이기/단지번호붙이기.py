import sys
from collections import deque
# sys.stdin = open("input.txt","r")

n = int(sys.stdin.readline())

route = [[int(c) for c in sys.stdin.readline().rstrip()] for _ in range(n)]

visited = [[False] * n for _ in range(n)]

house = []

def bfs(row,col):
    h = 0
    drow = [0,1,0,-1]
    dcol = [1,0,-1,0]

    que = deque()
    que.append((row,col))
    h+=1
    visited[row][col] = True

    while que:
        crow,ccol = que.popleft()
        for i in range(4):
            srow = crow+drow[i]
            scol = ccol+dcol[i]
            if srow<n and scol <n and srow>=0 and scol>=0:
                if not visited[srow][scol] and route[srow][scol] == 1:
                    que.append((srow,scol))
                    visited[srow][scol] = True
                    h+=1

    return h


result = 0
for i in range(n):
    for j in range(n):
        if not visited[i][j] and route[i][j] == 1:
            house.append(bfs(i,j))
            result +=1

house.sort()
print(result)
for ho in house:
    print(ho)