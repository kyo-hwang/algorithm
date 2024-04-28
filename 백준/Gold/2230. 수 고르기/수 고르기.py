import sys

n,m = map(int,sys.stdin.readline().split())

numLine = [int(sys.stdin.readline()) for _ in range(n)]
numLine.sort()

result = sys.maxsize

start = 0
end = 0
while end != n:
    interval = numLine[end] - numLine[start]
    if interval == m:
        result = interval
        break
    elif interval > m:
        if interval < result:
            result = interval
        start += 1
    else :
        end += 1

print(result)