import sys
sys.setrecursionlimit(100000)
# sys.stdin = open("input.txt","r")

n = int(sys.stdin.readline())

graph = [[] for _ in range(n)]

for _ in range(n-1):
    v,u,cost = map(int,sys.stdin.readline().split())
    graph[v-1].append((u-1,cost))
    graph[u-1].append((v-1,cost))

minResult = -sys.maxsize
visited = [False]*n

result = -sys.maxsize

def dfs(v):
    global result
    visited[v] = True
    firstMax = 0
    secondMax = 0
    for u,cost in graph[v]:
        if not visited[u]:
            curVal = cost+dfs(u)
            if curVal > firstMax:
                secondMax = firstMax
                firstMax = curVal
            elif curVal > secondMax:
                secondMax = curVal
    result = max(result,firstMax+secondMax)
    return firstMax

dfs(0)

print(result)