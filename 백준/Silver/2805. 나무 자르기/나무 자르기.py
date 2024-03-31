#https://www.acmicpc.net/problem/2805
import sys
# sys.stdin = open("input.txt","r")
#나무의 개수, 가져갈 나무 양
treeN,wAmount = map(int,sys.stdin.readline().rstrip().split())
#나무의 높이들
heights = [int(height) for height in sys.stdin.readline().rstrip().split(" ")]


#나무의 길이를 잘라서 가져갈 수 있는 양을 구함
def calAmount(treeHeights,cutHeight):
    amount =0
    for treeHeight in treeHeights:
        if treeHeight > cutHeight:
            amount += (treeHeight-cutHeight)
    return amount


def binarySearch(wAmount,heights):
    bottom = 0
    ceiling = max(heights)

    while ceiling >= bottom:
        cutHeight = (bottom + ceiling)//2

        amount = calAmount(heights,cutHeight)

        if amount == wAmount:
            return cutHeight
        elif amount < wAmount :
            ceiling = cutHeight-1 
        elif amount > wAmount:
            bottom = cutHeight+1

    return ceiling

print(binarySearch(wAmount,heights))