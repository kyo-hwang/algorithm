import sys
# sys.stdin = open("input.txt","r")

pN, sN = map(int,sys.stdin.readline().split())

words = sys.stdin.readline().rstrip().split()

words.sort()

result = set()

def isGather(char):
    gathers = ["a","e","i","o","u"]
    return char in gathers

def printResult(result):
    an = [c for c in result]
    an.sort()
    for char in an:
        sys.stdout.writelines(char)
    sys.stdout.writelines("\n")


def backtracking(index,gatherN):
    global sN
    global pN
    if len(result) == pN:
        if gatherN>=1 and gatherN<=(pN-2):
            printResult(result)
        return
    if index==sN:
        return
    bGather = gatherN
    result.add(words[index])
    if isGather(words[index]):
        gatherN+=1
    backtracking(index+1,gatherN)
    result.remove(words[index])
    gatherN = bGather
    backtracking(index+1,gatherN)

backtracking(0,0)
    