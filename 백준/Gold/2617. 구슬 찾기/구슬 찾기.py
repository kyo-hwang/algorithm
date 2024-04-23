import sys
from collections import deque

# sys.stdin = open("input.txt","r")

bN,eN = map(int,sys.stdin.readline().split())

graph = [[0]*bN for i in range(bN)]
dp = [[0]*bN for i in range(bN)]
inDgree = [0]*bN

for i in range(eN):
    light,heavy = map(int,sys.stdin.readline().split())
    if graph[light-1][heavy-1] == 0:
        graph[light-1][heavy-1] = 1
        inDgree[heavy-1] +=1

q = deque()
for i in range(len(inDgree)):
    if inDgree[i] == 0:
        q.append(i)
while q:
    v = q.pop()
    for u in range(bN):
        if graph[v][u] == 1:
            inDgree[u] -=1
            dp[u][v] = 1
            for i in range(bN):
                if dp[v][i] == 1:
                    dp[u][i] = 1
            if inDgree[u] == 0:
                q.append(u)

result = 0
for i in range(bN):
    cnt = 0
    for j in range(bN):
        if dp[i][j] == 1:
            cnt +=1
    if cnt > bN//2:
        result+=1

for i in range(bN):
    cnt = 0
    for j in range(bN):
        if dp[j][i] == 1:
            cnt +=1
    if cnt > bN//2:
        result+=1

print(result)
