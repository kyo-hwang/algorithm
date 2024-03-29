import sys
sys.setrecursionlimit(10000000)
# sys.stdin = open("input.txt","r")

priorityC = []

while True:
    try:
        w = input()
        priorityC.append(int(w))
    except EOFError:
        break


def find(left,right):
    # print("전위",priorityC[left])
    # print(priorityC[left:right+1])
    if right==left:
        print(priorityC[left])
        return
    elif left>right:
        return
    
    for i in range(left+1,right+2):
        bigLoc = i
        # print(i)
        if i == right+1:
            break
        if priorityC[i] > priorityC[left]:
            break

    find(left+1,bigLoc-1)
    find(bigLoc,right)
    print(priorityC[left])

find(0,len(priorityC)-1)