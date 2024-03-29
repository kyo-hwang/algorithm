import sys
from collections import deque
# sys.stdin = open("input.txt","r")

size,numCompare = (int(c) for c in sys.stdin.readline().rstrip().split(" "))

graph = [[]for row in range(size)]

in_degree = [0]*size

for i in range(numCompare):
    row,col = (int(coordinate) for coordinate in sys.stdin.readline().rstrip().split(" "))
    graph[row-1].append(col-1)
    in_degree[col-1] +=1

queue = deque()

#in-degree가 0인 아이들부터 삽입
for i in range(size):
    if in_degree[i] == 0:
        queue.append(i)

while queue:
    v = queue.popleft()
    print(v+1)
    for w in graph[v]:
        #간선 제거
        in_degree[w] -=1
        if in_degree[w] == 0:
            queue.append(w)
 