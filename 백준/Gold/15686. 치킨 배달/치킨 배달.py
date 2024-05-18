import sys
import copy
# sys.stdin = open("input.txt","r")

citySize,storeN = map(int,sys.stdin.readline().split())

city = [[int(c) for c in sys.stdin.readline().split()] for _ in range(citySize)]

houseLoc = []
storeLoc = []

for i in range(citySize):
    for j in range(citySize):
        if city[i][j] == 1:
            houseLoc.append([i,j,sys.maxsize])
        elif city[i][j] == 2:
            storeLoc.append((i,j))

def renewDistance(houseLoc,whichStore):
    storeRow,storeCol = storeLoc[whichStore]
    for i in range(len(houseLoc)):
        houseRow,houseCol,dis = houseLoc[i]
        houseLoc[i][2] = min(abs(storeRow-houseRow)+abs(storeCol-houseCol),dis)


def sumDistance(houseLoc):
    result  = 0
    for i,j,dis in houseLoc:
        result+=dis
    return result

totalDis = sys.maxsize
def backtracking(houseLoc,depth,whichStore):
    global storeN
    global totalDis
    if depth == storeN:
        totalDis = min(sumDistance(houseLoc),totalDis)
        return
    if whichStore >= len(storeLoc):
        return
    backtracking(copy.deepcopy(houseLoc),depth,whichStore+1)
    renewDistance(houseLoc,whichStore)
    backtracking(houseLoc,depth+1,whichStore+1)

backtracking(houseLoc,0,0)

print(totalDis)
    