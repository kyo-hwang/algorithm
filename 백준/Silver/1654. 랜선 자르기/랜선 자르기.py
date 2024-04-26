import sys
# sys.stdin = open("input.txt","r")

k,n = map(int,sys.stdin.readline().split())

lanLs = [int(sys.stdin.readline()) for _ in range(k)]

def findK(lanLs,lanLToSlice):
    n = 0
    for lanL in lanLs :
        n += lanL//lanLToSlice
    
    return n

left = 1
right = max(lanLs)
# print(right)
while left <= right:
    mid = (left+right)//2
    possibleK = findK(lanLs,mid)
    # print(possibleK)
    if possibleK< n:
        right = mid-1
    elif possibleK >=n:
        left = mid+1

print(right)