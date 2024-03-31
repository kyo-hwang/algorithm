import sys
# sys.stdin = open("input.txt","r")

numLen = int(sys.stdin.readline())

numLine = [int(n) for n in sys.stdin.readline().split(" ")]

operator = [int(o) for o in sys.stdin.readline().split(" ")]
# print(operator)
curMax = -sys.maxsize
curMin = sys.maxsize

def backTrack(curSum,i):
    global curMax
    global curMin
    # print(operator)

    if i==len(numLine):
        # print(curSum)
        if curSum > curMax:
            curMax = curSum

        if curSum < curMin:
            curMin = curSum
        return
    
    # print(i)
    for j in range(len(operator)):
        if operator[j] >=1:
            operator[j] -= 1
            if j == 0 :
                backTrack(curSum+numLine[i],i+1)
            if j == 1 :
                backTrack(curSum-numLine[i],i+1)
            if j == 2 :
                backTrack(curSum*numLine[i],i+1)
            if j == 3 :
                if (curSum<0 and numLine[i]>0) or (curSum>0 and numLine[i]<0) :
                    backTrack(abs(curSum)//abs(numLine[i])*-1,i+1)
                else :
                    backTrack(curSum//numLine[i],i+1)
            operator[j] += 1

backTrack(numLine[0],1)

print(curMax)
print(curMin)
