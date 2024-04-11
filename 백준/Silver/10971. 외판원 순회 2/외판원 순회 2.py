import sys
# sys.stdin = open("input.txt","r")
import time
start = time.time()

n = int(sys.stdin.readline())
graph = [list(map(int,sys.stdin.readline().split())) for _ in range(n)]

dp = [[dict() for _ in range(n)]for _ in range(n)]

for i in range(n):
    for j in range(n):
        minDist = dict()
        if graph[i][j] != 0:
            minDist[2**i|2**j] = graph[i][j]
            dp[i][j] = minDist
        else:
            dp[i][j] = minDist

t = 2
while(t<n):
    t+=1
    ndp = [[dict() for _ in range(n)]for _ in range(n)]
    for i in range(n):
        for j in range(n):
            for visit in dp[i][j].keys():
                for k in range(n):
                    if visit & 1<<k == 0:
                        if graph[k][i] != 0:
                            if visit|1<<k in ndp[k][j]:
                                ndp[k][j][visit|1<<k] = min(ndp[k][j][visit|1<<k],dp[i][j][visit]+graph[k][i])
                            else :
                                ndp[k][j][visit|1<<k] = dp[i][j][visit]+graph[k][i]
                        if graph[j][k] !=0:
                            if visit|1<<k in ndp[i][k]:
                                ndp[i][k][visit|1<<k] = min(ndp[i][k][visit|1<<k],dp[i][j][visit]+graph[j][k])
                            else :
                                ndp[i][k][visit|1<<k] = dp[i][j][visit]+graph[j][k]
    dp = ndp


curMin = sys.maxsize
for i in range(n):
    for j in range(n):
        for dis in dp[i][j].keys():
            if graph[j][i] != 0:
                minCan = dp[i][j][dis]+graph[j][i]
                if curMin > minCan:
                    curMin = minCan
print(curMin)
# print(ndp)
# for i in range(n):
#     for j in range(n):
#         for a in dp[i][j].keys():
#             print(i,j,bin(a),dp[i][j][a])
# print("time :", time.time() - start)