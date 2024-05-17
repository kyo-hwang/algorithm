import sys
# sys.stdin = open("input.txt","r")

groupN,pN = map(int,sys.stdin.readline().split())

groupToGirl = dict()
girlToGroup = dict()

for _ in range(groupN):
    groupName = sys.stdin.readline().rstrip()
    girlN = int(sys.stdin.readline())
    groupToGirl[groupName]=[]
    for _ in range(girlN):
        girlName = sys.stdin.readline().rstrip()
        groupToGirl[groupName].append(girlName)
        girlToGroup[girlName] = groupName
    groupToGirl[groupName].sort()

for _ in range(pN):
    pName = sys.stdin.readline().rstrip()
    problem = int(sys.stdin.readline())
    if problem == 0:
        for name in groupToGirl[pName]:
            sys.stdout.writelines(name+"\n")
    else :
        sys.stdout.writelines(girlToGroup[pName]+"\n")