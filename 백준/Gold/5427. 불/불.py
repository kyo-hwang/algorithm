import sys
from collections import deque
# sys.stdin = open("input.txt","r")

tc = int(sys.stdin.readline())

def findStart(graph,width,height,que):
    for i in range(height):
        for j in range(width):
            if graph[i][j] == "@":
                que.append((i,j))
                return
            
def addFire(graph,width,height,que):
    for i in range(height):
        for j in range(width):
            if graph[i][j] == "*":
                que.append((i,j))

def move(graph,row,col):
    global width
    global height

    dRow = [0,1,0,-1]
    dCol = [1,0,-1,0]
    locs = []
    for i in range(4):
        gRow,gCol = row+dRow[i],col+dCol[i]
        if gRow < height and gRow >=0 and gCol<width and gCol>=0:
            locs.append((gRow,gCol))
    return locs

def bfsFire(que,graph):
    temp = deque()
    while que:
        i,j = que.popleft()
        for gRow,gCol in move(graph,i,j):
            if graph[gRow][gCol] != "*" and graph[gRow][gCol] !="#":
                graph[gRow][gCol] = "*"
                temp.append((gRow,gCol))
    return temp

def bfs(que,graph):
    temp = deque()
    while que:
        i,j = que.popleft()
        for gRow,gCol in move(graph,i,j):
            if graph[gRow][gCol] != "*" and graph[gRow][gCol] !="#" and graph[gRow][gCol] !="@":
                graph[gRow][gCol] = "@"
                temp.append((gRow,gCol))
    return temp

def isMargin(row,col):
    global width
    global height
    if row == height-1 or row == 0 or col == 0 or col == width-1:
        return True

def canExit(que):
    for row,col in que:
        if isMargin(row,col):
            return True
    return False

for _ in range(tc):
    width,height = map(int,sys.stdin.readline().split())
    graph = [[c for c in sys.stdin.readline().rstrip()] for _ in range(height)]
    moveQ = deque()
    fireQ = deque()

    findStart(graph,width,height,moveQ)
    addFire(graph,width,height,fireQ)
    result = 0

    while True:
        if canExit(moveQ):
            print(result+1)
            break
        if not moveQ:
            print("IMPOSSIBLE")
            break
        fireQ = bfsFire(fireQ,graph)
        moveQ = bfs(moveQ,graph)
        result +=1