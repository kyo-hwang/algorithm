import sys
# sys.stdin = open("input.txt","r")

fN,lN = map(int,sys.stdin.readline().split())

def dfs(n,time,lN):
    if n > lN:
        return sys.maxsize
    if n==lN:
        return time
    
    return min(dfs(n*2,time+1,lN),dfs(n*10+1,time+1,lN))

result = dfs(fN,0,lN)
if result == sys.maxsize:
    print(-1)
else:
    print(result+1)