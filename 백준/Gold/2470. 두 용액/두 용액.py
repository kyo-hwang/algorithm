import sys
# sys.stdin = open("input.txt","r")

n = int(sys.stdin.readline())
solutions = [int(c) for c in sys.stdin.readline().split()]

solutions.sort()

left = 0
right = len(solutions)-1

density = sys.maxsize

optimalLeft = left
optimalRight = right

while left!=right:
    addResult = solutions[left]+solutions[right]
    if addResult==0:
        optimalLeft = left
        optimalRight = right
        break
    if abs(addResult)<density:
        optimalLeft = left
        optimalRight = right
        density = abs(addResult)
    if addResult>0:
        right-=1
    else:
        left+=1

print(min(solutions[optimalLeft],solutions[optimalRight]),max(solutions[optimalLeft],solutions[optimalRight]))