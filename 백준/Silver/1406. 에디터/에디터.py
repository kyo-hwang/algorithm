import sys
# sys.stdin = open("input.txt","r")

string = sys.stdin.readline().rstrip()

leftStack = []
rightStack = []

for c in string:
    leftStack.append(c)

cN = int(sys.stdin.readline())

commands = [sys.stdin.readline().split() for _ in range(cN)]

for command in commands:
    c = command[0]
    if c == "L":
        if leftStack:
            rightStack.append(leftStack.pop())
    elif c == "D":
        if rightStack:
            leftStack.append(rightStack.pop())
    elif c == "B":
        if leftStack:
            v = leftStack.pop()
    elif c =="P":
        leftStack.append(command[1])

for c in leftStack:
    sys.stdout.write(c)

while rightStack:
    v = rightStack.pop()
    sys.stdout.write(v)
