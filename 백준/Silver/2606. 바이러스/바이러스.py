import sys
from collections import deque
# sys.stdin = open("input.txt","r")

vN = int(sys.stdin.readline())
eN = int(sys.stdin.readline())

graph = [[] for _ in range(vN)]

for i in range(eN):
    row,col = (int(c) for c in sys.stdin.readline().split())
    graph[row-1].append(col-1)
    graph[col-1].append(row-1)

visited = [False]*vN

cnt = 0

def bfs():
    global cnt
    que = deque()
    que.append(0)
    visited[0] =True

    while que:
        v = que.popleft()
        # print(v)
        for w in graph[v]:
            if not visited[w]:
                visited[w] =True
                cnt += 1
                que.append(w)

bfs()

print(cnt)


