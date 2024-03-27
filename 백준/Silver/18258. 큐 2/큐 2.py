import sys
import collections
# sys.stdin = open("input.txt","r")
cN = int(sys.stdin.readline().rstrip())

que = collections.deque()

result = ""

for _ in range(cN):
    c = sys.stdin.readline().rstrip().split(" ")
    if c[0] == "push":
        que.append(c[1])
    elif c[0] == "pop":
        if not que:
            sys.stdout.writelines("-1\n")
        else:
            sys.stdout.writelines(que.popleft()+"\n")
    elif c[0] == "size":
        sys.stdout.writelines(str(len(que))+"\n")
    elif c[0] == "empty":
        if not que:
            sys.stdout.writelines("1\n")
        else:
            sys.stdout.writelines("0\n")
    elif c[0] == "front":
        if not que:
            sys.stdout.writelines("-1\n")
        else:
            sys.stdout.writelines(que[0]+"\n")
    elif c[0] == "back":
        if not que:
            sys.stdout.writelines("-1\n")
        else:
            sys.stdout.writelines(que[-1]+"\n")
