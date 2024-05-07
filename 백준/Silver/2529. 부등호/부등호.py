import sys
# sys.stdin = open("input.txt","r")
sys.setrecursionlimit(100000)

n = int(sys.stdin.readline())
comparator = sys.stdin.readline().split()

maxResult = -sys.maxsize
minResult = sys.maxsize

contained = [False]*10

def recur(cur,depth):
    global maxResult
    global minResult
    # print(cur)
    if depth == n+1:
        maxResult = max(cur,maxResult)
        minResult = min(cur,minResult)
        return
    
    for i in range(10):
        if depth == 0:
            contained[i] = True
            recur(i,depth+1)
            contained[i] = False
            continue

        if not contained[i]:
            if comparator[depth-1] == ">":
                if i < cur%10:
                    contained[i] = True
                    recur(cur*10+i,depth+1)
                    contained[i] = False
            else :
                if i > cur%10:
                    contained[i] = True
                    recur(cur*10+i,depth+1)
                    contained[i] = False
recur(0,0)

for i in range(n,-1,-1):
    first= maxResult//(10**i)
    print(first,end = "")
    maxResult -= first*(10**i)
print()
for i in range(n,-1,-1):
    first= minResult//(10**i)
    print(first,end = "")
    minResult -= first*(10**i)