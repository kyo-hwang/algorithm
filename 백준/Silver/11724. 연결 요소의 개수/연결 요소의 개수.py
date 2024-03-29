import sys
from collections import deque
# sys.stdin = open("input.txt","r")

vN, eN = map(int,sys.stdin.readline().split())

graph = [[] for _ in range(vN)]

for _ in range(eN):
    low,col = map(int,sys.stdin.readline().split())
    graph[low-1].append(col-1)
    graph[col-1].append(low-1)

visited = [False]*vN

def bfs(v):
    que = deque()
    que.append(v)
    visited[v] = True
    # print(v)

    while que:
        v = que.popleft()
        for w in graph[v]:
            if not visited[w]:
                visited[w] = True
                que.append(w)
                # print(w)

cN = 0

for v in range(vN):
    if not visited[v]:
        bfs(v)
        cN+=1

print(cN)



