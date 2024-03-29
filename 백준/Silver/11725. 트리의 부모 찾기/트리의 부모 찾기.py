import sys
from collections import deque
# sys.stdin = open("input.txt","r")

n = int(sys.stdin.readline())

graph = [[] for _ in range(n)]

for i in range(n-1):
    row,col = (int(c) for c in sys.stdin.readline().split())
    graph[row-1].append(col-1)
    graph[col-1].append(row-1)
    

visited = [False]*n
parent = [0]*n


def bfs():
    que = deque()
    que.append(0)
    visited[0] =True

    while que:
        v = que.popleft()
        for w in graph[v]:
            if not visited[w]:
                visited[w] =True
                parent[w] = v+1
                que.append(w)

bfs()
print(*parent[1:])