import sys
# sys.stdin = open("input.txt","r")

n = int(sys.stdin.readline())

graph = [[] for _ in range(n)]

for _ in range(n):
    command = sys.stdin.readline().split()
    # print(command)
    v = int(command[0])-1
    i = 1
    while(command[i]!="-1"):
        u = int(command[i])-1
        cost = int(command[i+1])
        graph[v].append((u,cost))
        graph[u].append((v,cost))
        i+=2

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

for v in range(n):
    if not visited[v]:
        dfs(v)

print(result)