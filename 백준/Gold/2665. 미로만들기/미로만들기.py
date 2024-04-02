import sys
from heapq import heappush, heappop
# sys.stdin = open("input.txt","r")
dcol = [1,0,-1,0]
drow = [0,1,0,-1]
n = int(sys.stdin.readline())

maze = [[int(c) for c in sys.stdin.readline().rstrip()] for _ in range(n)]

def dijkstra():
    global dcol
    global drow

    visited = [[False for _ in range(n)] for _ in range(n)]
    dis = [[sys.maxsize for _ in range(n)] for _ in range(n)]
    que = [(0,0,0)]
    dis[0][0] = 0
    while que:
        d,row,col = heappop(que)
        if not visited[row][col]:
            visited[row][col] = True
            for i in range(4):
                srow = drow[i] + row
                scol = dcol[i] + col
                if srow < n and scol < n and srow >=0 and scol >=0:
                    if not visited[srow][scol]:
                        if dis[srow][scol] > d + abs(1-maze[srow][scol]):
                            dis[srow][scol] = d + abs(1-maze[srow][scol])
                            heappush(que,(dis[srow][scol],srow,scol))
    return dis[-1][-1]

print(dijkstra())