import sys
sys.setrecursionlimit(10**6)
# sys.stdin = open("input.txt","r")

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
    # print("v",v)
    if outIn[v] == 1:
        for w in graph[v]:
            if outIn[w] == 1:
                route += 1
        return    
    else :
        directEnd = 0
        indirectEnd = 0
        canVisit[v] = False

        indirectEnd = []

        for w in graph[v]:
            if outIn[w] == 1:
                directEnd += 1
            elif outIn[w] == 0:
                if canVisit[w]:
                    # print("w",w,end=" ")
                    indirectEnd.append(dfs(w))

        indirectEnd.append(directEnd)
        route += directEnd*(directEnd-1)
        route += getSymmetrySum(indirectEnd)

    return sum(indirectEnd)

def getSymmetrySum(indirectEnd):
    symmetrySum = 0
    sumAll = sum(indirectEnd)
    for num in indirectEnd:
        symmetrySum += (sumAll-num)*num
    return symmetrySum

for v in range(vN):
    if canVisit[v]:
        dfs(v)

print(route)
