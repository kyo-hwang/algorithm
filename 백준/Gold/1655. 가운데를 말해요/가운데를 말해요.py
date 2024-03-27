import sys
from queue import PriorityQueue
from heapq import heappop,heappush

# sys.stdin = open("input.txt","r")

n = int(sys.stdin.readline().rstrip())
nums = [int(sys.stdin.readline().rstrip()) for _ in range(n)]

minHeap = []
maxHeap = []

heappush(minHeap,max(nums[0],nums[1]))
heappush(maxHeap,-min(nums[0],nums[1]))
print(nums[0])
print(-maxHeap[0])

for i in range(2,n):
    numToPut = nums[i]
    bigMinmum = minHeap[0]
    smallMax = -maxHeap[0]

    # 중간값 가져와야지
    if numToPut>=bigMinmum:
        mid = heappop(minHeap)
        heappush(minHeap,numToPut)
    elif numToPut<=smallMax:
        mid = -heappop(maxHeap)
        heappush(maxHeap,-numToPut)
    else:
        mid = numToPut

    sys.stdout.writelines(str(mid)+"\n")

    #mid값 넣어줘야지
    if len(maxHeap) == len(minHeap):
        heappush(minHeap,mid)
    else:
        heappush(maxHeap,-mid)





