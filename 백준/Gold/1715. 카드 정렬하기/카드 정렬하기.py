import sys
from heapq import heappop,heapify,heappush
# sys.stdin = open("input.txt","r")

n = int(sys.stdin.readline())
cards = [int(sys.stdin.readline()) for _ in range(n)]
heapify(cards)


result = 0
while len(cards)!=1:
    add = heappop(cards)+heappop(cards)
    result+=add
    heappush(cards,add)

print(result)