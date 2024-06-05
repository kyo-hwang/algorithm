import sys
import math
# sys.stdin = open("input.txt")

n = int(sys.stdin.readline())

for i in range(n):
    a,b,aS,bS = map(int,sys.stdin.readline().split())
    value = aS
    maxi = math.lcm(a,b)
    while(value<=maxi):
        if((value-bS) % b ==0):
            break
        value+= a
    
    if(value>maxi):
        sys.stdout.writelines("-1\n")
    else:
        sys.stdout.writelines(str(value)+"\n")