import sys
sys.setrecursionlimit(10**6)

vN = int(sys.stdin.readline())

outIn = []

for i in sys.stdin.readline().strip():
    outIn.append(int(i))

graph = [[]for _ in range(vN)]

for _ in range(vN-1):
    row,col = (int(c) for c in sys.stdin.readline().split())
    graph[row-1].append(col-1)
    graph[col-1].append(row-1)

route = 0

canVisit = [True] * vN

def dfs(v):
    global route
    if outIn[v] == 1:
        for w in graph[v]:
            if outIn[w] == 1:
                route += 1
        return
    
    else :
        directEnd = 0
        indirectEnd = 0
        canVisit[v] = False

        for w in graph[v]:
            if outIn[w] == 1:
                directEnd += 1
            elif outIn[w] == 0:
                if canVisit[w]:
                    indirectEnd += dfs(w)
        
        route += directEnd*(directEnd-1)
        route += 2*directEnd*indirectEnd


    return indirectEnd+directEnd

for v in range(vN):
    if canVisit[v]:
        dfs(v)

print(route)
