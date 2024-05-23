import sys
# sys.stdin = open("input.txt","r")

n, m = map(int,sys.stdin.readline().split())

def howNum(n,k):
    mul = k
    Num = 0
    while(mul<=n):
        Num += n//mul
        mul = mul* k
    return Num

twoNum = howNum(n,2) - howNum(n-m,2) -howNum(m,2)
fiveNum = howNum(n,5) - howNum(n-m,5) -howNum(m,5)

print(min(twoNum,fiveNum))
