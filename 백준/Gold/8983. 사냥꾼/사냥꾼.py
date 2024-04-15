import sys
# sys.stdin = open("input.txt","r")

gunNum,aN,distance = [int(c) for c in sys.stdin.readline().split()]

gunLoc = [int(c) for c in sys.stdin.readline().split()]

aL = [[int(c) for c in sys.stdin.readline().split()] for _ in range(aN)]

gunLoc.sort()

def findNear(x):
    left = 0
    right = len(gunLoc)-1

    while left<=right:
        mid = (left+right)//2
        if x == gunLoc[mid]:
            return mid
        elif x > gunLoc[mid]:
            left = mid+1
            if left > len(gunLoc)-1:
                return len(gunLoc)-1
        elif x < gunLoc[mid]:
            right = mid-1
            if right < 0:
                return 0

    if (gunLoc[left]-x) > (gunLoc[right]-x):
        return left
    else :
        return right

result = 0
for x,y in aL:
    aniDis = abs(x-gunLoc[findNear(x)])+y
    if aniDis <= distance:
        result += 1

print(result)