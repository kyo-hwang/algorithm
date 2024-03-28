import sys
from collections import deque

# sys.stdin = open("input.txt","r")

vN,eN,sV = (int(c) for c in sys.stdin.readline().rstrip().split(" "))
graph =[[0 for i in range(vN)] for j in range(vN)]

for _ in range(eN):
    row,col = (int(c) for c in sys.stdin.readline().rstrip().split(" "))
    graph[row-1][col-1] = 1 
    graph[col-1][row-1] = 1 


visited = [False for _ in range(vN)]

def dfs(v):
    visited[v] = True
    print(v+1,end =" ")
    for w in range(vN):
        # print(w,"sdca")
        if graph[v][w] == 1 and not visited[w]:
            # print(w,"ss")
            dfs(w)

def dfsNotRecur(v):
    stack = [v]

    while stack:
        v = stack.pop()
        if not visited[v]:
            print(v+1,end =" ")
            visited[v] = True
            for w in range(vN-1,-1,-1):
                if graph[v][w] == 1:
                    stack.append(w)

def bfs(v):
    que = deque()
    visited[v] = True
    print(v+1,end=" ")
    que.append(v)

    while que:
        v = que.popleft()
        for w in range(vN):
            if graph[v][w] == 1 and not visited[w]:
                print(w+1,end=" ")
                visited[w] = True
                que.append(w)



# dfs(sV-1)
visited = [False for _ in range(vN)]
dfs(sV-1)
print()
visited = [False for _ in range(vN)]
bfs(sV-1)