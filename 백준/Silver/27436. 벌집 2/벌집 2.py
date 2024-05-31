import sys
# sys.stdin = open("input.txt","r")

n = int(sys.stdin.readline())
# print(n)
left = 1
right = 10**10

def multi(mid):
    return 3*mid**2-3*mid+1


while(left<=right):
    mid = (left+right)//2
    num = multi(mid)
    if num == n:
        left = mid
        break
    elif num < n:
        left = mid+1
    else :
        right = mid-1

print(left)
