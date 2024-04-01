import sys
from heapq import heappush, heappop
# sys.stdin = open("input.txt","r")

cN = int(sys.stdin.readline())
bN = int(sys.stdin.readline())

graph = [[] for _ in range(cN)]

for _ in range(bN):
    v,u,w = map(int,sys.stdin.readline().split())
    graph[v-1].append((u-1,w))

s,dest = map(int,sys.stdin.readline().split())
visited = [False]*cN
dist = [sys.maxsize]*cN

pque = [(0,s-1)]
dist[s-1] = 0

while pque:
    w,v = heappop(pque)
    if not visited[v]:
        visited[v] = True
        for u,uw in graph[v]:
            if not visited[u]:
                dist[u] = min(dist[u],dist[v]+uw)
                heappush(pque,(dist[u],u))

print(dist[dest-1])