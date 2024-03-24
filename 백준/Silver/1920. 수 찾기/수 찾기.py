import sys 
# sys.stdin = open("input.txt","r")

n = int(sys.stdin.readline().rstrip())

numLine = [int(i) for i in input().split(" ")]

nToFind = int(sys.stdin.readline().rstrip())

numLineToFind = [int(i) for i in input().split(" ")]

numLine.sort()

def binarySearch(num,numLine):
    left = 0
    right = len(numLine)-1

    while left <= right:
        mid = (left+right)//2
        # print(mid," nid")
        if num == numLine[mid]:
            return mid
        elif num < numLine[mid]:
            right = mid-1
        elif num > numLine[mid]:
            left = mid+1

    return -1

for numToFind in numLineToFind:
    if binarySearch(numToFind,numLine) >= 0:
        print(1)
    else:
        print(0)