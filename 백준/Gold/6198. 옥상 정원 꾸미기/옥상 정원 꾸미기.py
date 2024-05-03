import sys
# sys.stdin = open("input.txt","r")
n = int(sys.stdin.readline())

buildings = [int(sys.stdin.readline()) for _ in range(n)]

stack = []

cnt = 0

for i in range(n):
    while stack:
        if buildings[i] >= stack[-1]:
            stack.pop()
        else:
            break
    cnt += len(stack)
    stack.append(buildings[i])

print(cnt)