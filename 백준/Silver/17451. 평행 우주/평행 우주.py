import sys
# sys.stdin = open("input.txt","r")

n = int(sys.stdin.readline())
planets = [int(c) for c in sys.stdin.readline().split()]

cur = planets[-1]
for i in range(n-2,-1,-1):
    if cur <= planets[i]:
        cur = planets[i]
        continue

    k= cur//planets[i]
    if cur % planets[i]!=0:
        cur = (k+1)*planets[i]

print(cur)