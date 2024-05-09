import sys
from collections import deque
# sys.stdin = open("input.txt","r")

t = int(sys.stdin.readline())

def makeNumLine(arrayString):
    if len(arrayString)==2:
        return deque()
    numLine = arrayString[1:len(arrayString)-1].split(",")
    return deque(map(int,numLine))

    
for _ in range(t):
    lang = sys.stdin.readline().rstrip()
    n = int(sys.stdin.readline())
    numLine = makeNumLine(sys.stdin.readline().rstrip())
    reverse = False
    error = False
    for c in lang:
        if c =="D":
            if not numLine:
                print("error")
                error=True
                break
            if reverse :
                numLine.pop()
            else:
                numLine.popleft()
        else :
            reverse = not reverse
    result = ""
    if not error:
        if not numLine:
            print("[]")
            continue
        elif not reverse:
            result+="["
            while numLine:
                result+=str(numLine.popleft())+","
            result = result[:len(result)-1]
            result+="]"
        else:
            result="["
            while numLine:
                result+=str(numLine.pop())+","
            result = result[:len(result)-1]
            result+="]"
        print(result)
