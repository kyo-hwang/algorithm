import sys
# sys.stdin = open("input.txt","r")

n = int(sys.stdin.readline())
balls = sys.stdin.readline().rstrip()

left = balls[0]
right = balls[-1]

leftTotal = 0
rightTotal = 0

for i in range(0,len(balls)):
    if balls[i] == left:
        leftTotal += 1
    else:
        break

for i in range(len(balls)-1,-1,-1):
    if balls[i] == right:
        rightTotal += 1
    else:
        break

rS = 0
bS = 0
for ball in balls:
    if ball == "R":
        rS+=1

bS = len(balls)-rS

result = sys.maxsize
if left == "R":
    result = min(result,rS-leftTotal)
else :
    result = min(result,bS-leftTotal)

if right == "R":
    result = min(result,rS-rightTotal)
else:
    result = min(result,bS-rightTotal)

result = min(result,bS)
result = min(result,rS)

print(result)