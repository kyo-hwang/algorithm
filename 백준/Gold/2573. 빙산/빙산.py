import sys
from collections import deque
# sys.stdin = open("input.txt","r")

dCol = [1,0,-1,0]
dRow = [0,1,0,-1]
def countWater(row,col,graph):
    cnt = 0
    for i in range(4):
        if graph[row+dRow[i]][col + dCol[i]] == 0:
            cnt+=1
    return cnt

row,col = map(int,sys.stdin.readline().split())

graph = [list(map(int,sys.stdin.readline().split())) for _ in range(row)]



year  = 0

while True:
    iceBergN = 0
    graphToBeChanged = [[0]*col for _ in range(row)]
    visited = [[False]*col for _ in range(row)]
    # print(year)
    # print(graph)
    for i in range(1,row):
        for j in range(1,col):
            if not visited[i][j] and graph[i][j] != 0:
                iceBergN +=1
                # print("i",iceBergN)
                que = deque()
                que.append((i,j))
                graphToBeChanged[i][j] = max(0,graph[i][j]  - countWater(i,j,graph))
                visited[i][j] = True
                while que:
                    iRow,iCol = que.popleft()
                    for k in range(4):
                        if graph[iRow+dRow[k]][iCol+dCol[k]] != 0 and not visited[iRow+dRow[k]][iCol+dCol[k]]:
                            que.append((iRow+dRow[k],iCol+dCol[k]))
                            visited[iRow+dRow[k]][iCol+dCol[k]] = True
                            graphToBeChanged[iRow+dRow[k]][iCol+dCol[k]] = max(0,graph[iRow+dRow[k]][iCol+dCol[k]]  - countWater(iRow+dRow[k],iCol+dCol[k],graph))
    graph = graphToBeChanged
    # print(graph)
    # print(iceBergN)
    if iceBergN !=1:
        break
    year += 1

if iceBergN == 0:
    print(0)
else:
    print(year)

