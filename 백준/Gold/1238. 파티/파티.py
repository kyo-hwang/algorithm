import sys
from heapq import heappush,heappop
# sys.stdin = open("input.txt","r")

vN,eN,des = map(int,sys.stdin.readline().split())

outGraph = [[] for _ in range(vN)]
inGraph = [[] for _ in range(vN)]

for _ in range(eN):
    v,u,w = map(int,sys.stdin.readline().split())
    outGraph[v-1].append((u-1,w))
    inGraph[u-1].append((v-1,w))

def dijkstra(graph,start):
    distances = [sys.maxsize]*len(graph)
    distances[start] = 0
    pq = [(0,start)]
    while pq:
        vCost,v = heappop(pq)
        if vCost>distances[v]:
            continue
        for u,uvcost in graph[v]:
            if uvcost+vCost < distances[u]:
                distances[u] = uvcost+vCost
                heappush(pq,(uvcost+vCost,u))

    return distances

result = -sys.maxsize
go = dijkstra(inGraph,des-1)
back = dijkstra(outGraph,des-1)

for i in range(vN):
    result = max(go[i]+back[i],result)    

print(result)