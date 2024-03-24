import sys
import copy

n = int(sys.stdin.readline().rstrip())

routes = [[int(j) for j in sys.stdin.readline().rstrip().split(" ")] for _ in range(n)]
visited = [False]*n

global shortest
shortest = sys.maxsize

def find_route(st,loc,distance,start_pos):
    global shortest
    if st == 0 :
        if routes[loc][start_pos]!=0 :
            shortest = min(distance+routes[loc][start_pos],shortest)
        return

    for i in range(n):
        if not (visited[i] or routes[loc][i]==0):
            visited[i] = True
            distance = distance + routes[loc][i]
            find_route(st-1,i,distance,start_pos)
            visited[i] = False
            distance = distance - routes[loc][i] 
           

for i in range(n):
    visited[i] = True
    find_route(n-1,i,0,i)
    visited[i] = False

print(shortest)