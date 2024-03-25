#https://www.acmicpc.net/problem/2805

import sys
# sys.stdin = open("input.txt","r")

plan = sys.stdin.readline().rstrip().split(" ")
numOfTree = int(plan[0])
treeLen = int(plan[1])

heights = [int(height) for height in sys.stdin.readline().rstrip().split(" ")]

heights.sort()

def getMinBig(numToFind,heights):
    left = 0
    right = len(heights)-1

    while right>left:
        # print(right)
        mid = (left+right)//2
        # print(mid)

        if heights[mid] == numToFind:
            return mid
        
        elif heights[mid] < numToFind:
            left = mid+1

        elif heights[mid] > numToFind:
            right = mid

    
    return left


def check(treeHeights,cutHeight):
    sumHeight =0
    for treeHeight in treeHeights:
        if treeHeight > cutHeight:
            sumHeight += (treeHeight-cutHeight)

    return sumHeight


def binarySearch(m,heights):
    left = 0
    right = heights[-1]

    while left <= right:
        #mid는 현재 자를 높이
        mid = (left+right)//2
        # print("mid",mid)
        #자를 수 있는 나무의 시작 위치를 찾음
        currM = check(heights,mid)

        if currM == m:
            return mid
        #중간으로 두고 자르고 남은 길이가 원하는 길이 보다 작으면 더 아래에서 잘라야지
        elif currM < m :
            right = mid-1
        elif currM > m:
            left = mid+1


    return right

print(binarySearch(treeLen,heights))