#https://www.acmicpc.net/problem/2805
import sys
# sys.stdin = open("input.txt","r")
#나무의 개수, 가져갈 나무 양
treeN,wAmount = map(int,sys.stdin.readline().rstrip().split())
#나무의 높이들
heights = [int(height) for height in sys.stdin.readline().rstrip().split(" ")]


#나무의 길이를 잘라서 가져갈 수 있는 양을 구한다.
def calAmount(treeHeights,cutHeight):
    amount =0
    for treeHeight in treeHeights:
        if treeHeight > cutHeight:
            amount += (treeHeight-cutHeight)
    return amount

heights.sort()
fromSum = [heights[0]]
for i in range(1,len(heights)):
    fromSum.append(heights[i]+fromSum[i-1])

def calAmountRapidly(treeHeights,fromSum,cutHeight):
    left = 0
    right = len(treeHeights)-1
    
    #톱의 높이보다 높은 나무 중 최소 값은 left
    while left<=right:
        mid = (left+right)//2
        if cutHeight == treeHeights[mid]:
            left = mid
            break
        elif cutHeight > treeHeights[mid]:
            left = mid+1
        elif cutHeight < treeHeights[mid]:
            right = mid-1

    if left==0:
        result = fromSum[-1] - (len(treeHeights)-left)*cutHeight
    else:
        result = fromSum[-1] - fromSum[left-1] -(len(treeHeights)-left)*cutHeight
    return result

def binarySearch(wAmount,heights):
    bottom = 0
    ceiling = heights[-1]

    while ceiling >= bottom:
        cutHeight = (bottom + ceiling)//2
        amount = calAmountRapidly(heights,fromSum,cutHeight)
        # print(amount)

        if amount == wAmount:
            return cutHeight
        elif amount < wAmount :
            ceiling = cutHeight-1 
        elif amount > wAmount:
            bottom = cutHeight+1

    return ceiling
# print(fromSum)
print(binarySearch(wAmount,heights))