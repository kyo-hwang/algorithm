import sys
from queue import PriorityQueue

# sys.stdin = open("input.txt","r")

n = int(sys.stdin.readline().rstrip())
commands = [int(sys.stdin.readline().rstrip()) for _ in range(n)]

priorityQueue = PriorityQueue()

for command in commands:
    if command != 0:
        priorityQueue.put(-command)
    else :
        if priorityQueue.empty():
            sys.stdout.writelines("0\n")
        else:
            sys.stdout.writelines(str(-priorityQueue.get())+"\n")