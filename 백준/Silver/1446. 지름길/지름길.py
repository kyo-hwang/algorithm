import sys
# sys.stdin = open("input.txt","r")

n,d = map(int,sys.stdin.readline().split())

shortcuts = [[int(c) for c in sys.stdin.readline().split()] for _ in range(n)]

shortcuts.sort(key=lambda x:x[0])

distance = list(range(d+1))

nextShortcut = 0

def fillDp(cur,distance,nextShortcut,shortcuts):
    global d
    global n
    while cur == shortcuts[nextShortcut][0]:
        if shortcuts[nextShortcut][1] <= d:
            distance[shortcuts[nextShortcut][1]] = min(distance[shortcuts[nextShortcut][1]],distance[cur]+shortcuts[nextShortcut][2])
        nextShortcut+=1
        if nextShortcut >= n:
                return nextShortcut
    
    return nextShortcut

for cur in range(d+1):
    if cur != 0:
        distance[cur] = min(distance[cur-1]+1,distance[cur])
    if nextShortcut<n and shortcuts[nextShortcut][0] == cur:
        nextShortcut = fillDp(cur,distance,nextShortcut,shortcuts)
        # print(distance)
        # print(nextShortcut)

print(distance[-1])