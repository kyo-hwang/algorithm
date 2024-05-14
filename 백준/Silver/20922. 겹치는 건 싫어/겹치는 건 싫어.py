import sys
# sys.stdin = open("input.txt","r")

n,dn = map(int,sys.stdin.readline().split())
numLine = [int(c) for c in sys.stdin.readline().split()]

size = max(numLine)

dp = [0]*(size+1)

left = 0
right = 0

curLen = 0
result = 0

while(right<n):
    if dp[numLine[right]] >= dn:
        dp[numLine[left]] -= 1
        left+=1
        curLen -=1
    else :
        dp[numLine[right]] +=1
        curLen+=1
        right+=1
    # print(result)
    result = max(curLen,result)

print(result)