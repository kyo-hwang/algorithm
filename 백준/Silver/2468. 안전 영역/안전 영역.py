import sys
from collections import deque
# sys.stdin= open("input.txt","r")

n = int(sys.stdin.readline().rstrip())

heights = [[int(i) for i in sys.stdin.readline().rstrip().split(" ")] for _ in range(n)]

max_height = max([max(row_heights) for row_heights in heights])



def getCanVisit(waterHeight,heights):
    canVisit = [[True for _ in range(n)] for _ in range(n)]
    for i in range(n):
        for j in range(n):
            if waterHeight>= heights[i][j]:
                canVisit[i][j] = False

    return canVisit

def valid(x,y):
    if x>=0 and x<n and y >=0 and y<n:
        return True
    
    return False

maxNumSafeArea = 0
global dx,dy
#상하좌우
dx = [-1,1,0,0]
dy = [0,0,-1,1]

def bfs(r,c,canVisit):
    queue = deque()
    queue.append((r,c))
    canVisit[r][c] = False
    #인접한 안전 위치 찾기
    while queue:
        front = queue.popleft()
        for k in range(4):
            nx = front[0]+dx[k]
            ny = front[1]+dy[k]
            if valid(nx,ny):
                if canVisit[nx][ny]:
                    canVisit[nx][ny] = False
                    queue.append((nx,ny))

for waterHeight in range(101):
    numOfSafeArea = 0
    canVisit = getCanVisit(waterHeight,heights)
    
    for i in range(n):
        for j in range(n):
            if canVisit[i][j]:
                numOfSafeArea+=1
                bfs(i,j,canVisit)
                

    maxNumSafeArea = max(maxNumSafeArea,numOfSafeArea)

print(maxNumSafeArea)

    
