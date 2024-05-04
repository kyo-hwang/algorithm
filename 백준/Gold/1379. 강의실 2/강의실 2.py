import sys
from heapq import heappop,heappush
# sys.stdin = open("input.txt","r")

n = int(sys.stdin.readline())
order = [[int(c) for c in sys.stdin.readline().split()] for _ in range(n)]
order.sort(key=lambda x:x[1])

whereBelong = [sys.maxsize]*(n+1)
pq = []

maxRoom = 0
for curNum,curStart,curLast in order:
    if pq and pq[0][0] <= curStart:
        last,roomNum = heappop(pq)
        whereBelong[curNum] = str(roomNum)
        heappush(pq,(curLast,roomNum))
    else:
        maxRoom+=1
        whereBelong[curNum] = str(maxRoom)
        heappush(pq,(curLast,maxRoom))

print(maxRoom)

for i in range(1,n+1):
    sys.stdout.writelines(whereBelong[i]+"\n")