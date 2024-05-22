import sys
from collections import deque
# sys.stdin = open("input.txt","r")

n = int(sys.stdin.readline())

graph = []

for _ in range(n):
    graph.append(sys.stdin.readline().rstrip())

visited = [[False]*n for _ in range(n)]

def move(i,j):
    global n
    dRow = [0,1,0,-1]
    dCol = [1,0,-1,0]
    possibleGo = []
    for k in range(4):
        tRow = i+dRow[k]
        tCol = j+dCol[k]
        if(tRow<n and tRow>=0 and tCol<n and tCol>=0):
            possibleGo.append((tRow,tCol))
    return possibleGo

normal = 0
for i in range(n):
    for j in range(n):
        if not visited[i][j]:
            q = deque()
            q.append((i,j))
            visited[i][j] = True
            color = graph[i][j]
            while q:
                row, col = q.popleft()
                possibleGo = move(row,col)
                for tRow,tCol in possibleGo:
                    if not visited[tRow][tCol] and graph[tRow][tCol] == color:
                        q.append((tRow,tCol))
                        visited[tRow][tCol] = True

            normal+=1

abnormal = 0
visited = [[False]*n for _ in range(n)]
for i in range(n):
    for j in range(n):
        if not visited[i][j]:
            q = deque()
            q.append((i,j))
            visited[i][j] = True
            color = graph[i][j]
            if color == "B":
                while q:
                    row, col = q.popleft()
                    possibleGo = move(row,col)
                    for tRow,tCol in possibleGo:
                        if not visited[tRow][tCol] and graph[tRow][tCol] == color:
                            q.append((tRow,tCol))
                            visited[tRow][tCol] = True
                abnormal+=1
            else:
                while q:
                    row, col = q.popleft()
                    possibleGo = move(row,col)
                    for tRow,tCol in possibleGo:
                        if not visited[tRow][tCol] and graph[tRow][tCol] != "B":
                            q.append((tRow,tCol))
                            visited[tRow][tCol] = True
                abnormal+=1

print(normal,abnormal)
