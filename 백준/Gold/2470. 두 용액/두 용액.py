import sys
# sys.stdin = open("input.txt","r")

n  =  int(sys.stdin.readline())
solutions = [int(c) for c in sys.stdin.readline().split()]

negative = []
positive = []

for i in range(n):
    if solutions[i] == 0:
        negative.append(0)
        positive.append(0)
    elif solutions[i]<0:
        negative.append(solutions[i])
    else:
        positive.append(solutions[i])

smallLoc = [0,0]

negative.sort()
positive.sort()

def ifSmall(add1,add2):
    global result
    # print(add1,add2)
    # print("result",result,"add",abs(add1+add2))
    if result > abs(add1+add2):
        result = abs(add1+add2)
        smallLoc[0],smallLoc[1] = add1,add2

def binarySearch(num,solutions):
    left = 0
    right = len(solutions)-1
    while left<=right:
        mid = (left+right)//2
        if abs(num)==abs(solutions[mid]):
            return mid
        elif abs(solutions[mid]) < abs(num):
            left = mid+1
        else:
            right = mid-1
    #좌우에 가장 가까운 값이 있을 수 있음.
    if right < 0:
        return 0
    elif left>len(solutions)-1:
        return len(solutions)-1
    else:
        if abs(solutions[right]+num) < abs(solutions[left]+num):
            return right
        else:
            return left

result = sys.maxsize

if len(negative) >= 2:
    ifSmall(negative[-1],negative[-2])
if len(positive) >= 2:
    ifSmall(positive[0],positive[1])

if positive:
    for num in negative:
        if num != 0:
            i = binarySearch(num,positive)
            # print(positive[i])
            ifSmall(num,positive[i])

smallLoc.sort()
print(*smallLoc)