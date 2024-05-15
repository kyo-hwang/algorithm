import sys
from collections import deque
# sys.stdin = open("input.txt","r")

myLoc,sisLoc = map(int,sys.stdin.readline().split())

que = deque([(myLoc,0)])

result = sys.maxsize
visited = [False]*100001

while que:
    # print(que)
    curLoc,time = que.popleft()
    if curLoc == sisLoc:
        result = time
        break
    if curLoc*2<=100000 and not visited[curLoc*2]:
        visited[curLoc*2] = True
        que.append((curLoc*2,time+1))
    if curLoc+1<=100000 and not visited[curLoc+1]:
        visited[curLoc+1] = True
        que.append((curLoc+1,time+1))
    if curLoc-1>=0 and not visited[curLoc-1]:
        visited[curLoc-1] = True
        que.append((curLoc-1,time+1))

print(result)