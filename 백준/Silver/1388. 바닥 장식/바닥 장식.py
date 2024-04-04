import sys

row,col = map(int,sys.stdin.readline().split())

bottom = [[c for c in sys.stdin.readline().rstrip() ]for _ in range(row)]

result = 0

for i in range(row):
    before = ""
    for j in range(col):
        if bottom[i][j] == "-":
            if before != "-":
                result+=1
        before = bottom[i][j]

for j in range(col):
    before = ""
    for i in range(row):
        if bottom[i][j] == "|":
            if before != "|":
                result+=1
        before = bottom[i][j]

print(result)