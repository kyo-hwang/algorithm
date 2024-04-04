import sys
# sys.stdin = open("input.txt","r")

n = int(sys.stdin.readline())


a_1 = 2
a_2 = 1
if n == 1:
    print(a_2)
elif n == 2:
    print(a_1)
else : 
    for i in range(n-2):
        a = (a_1 + a_2)%15746
        a_2 = a_1
        a_1 = a
    print(a)