import sys
import copy
# sys.stdin = open("input.txt","r")

citySize,storeN = map(int,sys.stdin.readline().split())

city = [[int(c) for c in sys.stdin.readline().split()] for _ in range(citySize)]

houseLoc = []
houseCost = []
storeLoc = []

for i in range(citySize):
    for j in range(citySize):
        if city[i][j] == 1:
            houseLoc.append([i,j])
            houseCost.append(sys.maxsize)
        elif city[i][j] == 2:
            storeLoc.append((i,j))

def renewDistance(houseCost,whichStore):
    storeRow,storeCol = storeLoc[whichStore]
    for i in range(len(houseLoc)):
        houseRow,houseCol = houseLoc[i]
        houseCost[i] = min(abs(storeRow-houseRow)+abs(storeCol-houseCol),houseCost[i])

def sumDistance(houseCost):
    result  = 0
    for dis in houseCost:
        result+=dis
    return result

totalDis = sys.maxsize
def backtracking(houseCost,depth,whichStore):
    global storeN
    global totalDis
    if depth == storeN:
        totalDis = min(sumDistance(houseCost),totalDis)
        return
    if whichStore >= len(storeLoc):
        return
    backtracking(copy.deepcopy(houseCost),depth,whichStore+1)
    renewDistance(houseCost,whichStore)
    backtracking(houseCost,depth+1,whichStore+1)

backtracking(houseCost,0,0)

print(totalDis)
    