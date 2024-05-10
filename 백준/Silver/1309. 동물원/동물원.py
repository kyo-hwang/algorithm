import sys
# sys.stdin = open("input.txt","r")

n = int(sys.stdin.readline())

zero,one = 1,0

for _ in range(n):
    tempZero = zero
    zero = (zero + one) % 9901
    one  = (zero + tempZero) % 9901

print((zero+one)%9901)