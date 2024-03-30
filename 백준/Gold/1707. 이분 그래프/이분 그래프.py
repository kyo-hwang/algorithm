import sys
sys.setrecursionlimit(10**6)
# sys.stdin = open("input.txt","r")

tN = int(sys.stdin.readline())

def dfs(v,parent,depth):
    global graph
    global visited
    global where
    global answer

    if visited[v]:
        return
    visited[v] = True
    
    where[v] = depth%2

    for w in graph[v]:
        if answer == "NO":
            return
        if not visited[w]:
            dfs(w,v,depth+1)
        elif visited[w]:
            if w!=parent:
                #같은 홀수 또는 짝수인지
                if where[v] == where[w]:
                    answer = "NO"
                    return

for _ in range(tN):
    vN,eN = (int(c) for c in sys.stdin.readline().split())
    graph = [[] for _ in range(vN)]
    for _ in range(eN):
        row,col = (int(c) for c in sys.stdin.readline().split())
        graph[row-1].append(col-1)
        graph[col-1].append(row-1)

    where = [-1]*vN

    visited = [False]*vN
    
    answer = "YES"
    for v in range(vN):
        if not visited[v]:
            dfs(v,-1,0)
        if answer =="NO":
            break
    print(answer)