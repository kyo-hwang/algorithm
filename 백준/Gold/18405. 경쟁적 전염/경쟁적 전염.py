import sys
from heapq import heappop,heappush

# sys.stdin = open("input.txt","r")

n,k = map(int,sys.stdin.readline().split())

graph = [[int(c) for c in sys.stdin.readline().split()] for _ in range(n)]

s,x,y = map(int,sys.stdin.readline().split())

def bfs(s):
    pq = []
    for i in range(n):
        for j in range(n):
            if graph[i][j] > 0:
                heappush(pq,(graph[i][j],i,j))
    drow = [0,1,0,-1]
    dcol = [1,0,-1,0]

    for j in range(s):
        nextpq = []
        while pq:
            w,crow,ccol = heappop(pq)
            for i in range(4):
                srow = drow[i]+crow
                scol = dcol[i]+ccol
                if srow<n and scol<n and srow>=0 and scol>=0:
                    if graph[srow][scol] == 0:
                        graph[srow][scol] = w
                        heappush(nextpq,(w,srow,scol))
        pq = nextpq

bfs(s)
print(graph[x-1][y-1])