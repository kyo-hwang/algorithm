import sys
# sys.stdin = open("input.txt","r")

n = int(sys.stdin.readline())

num = list(range(n+1))
isPrime = [True]*(n+1)

prime = []

for i in range(2,n+1):
    if isPrime[i]:
        prime.append(i)
        for j in range(i,n+1,i):
            isPrime[j] = False
result = 0
sectionSum = 0

start = -1
end = -1
# print(prime)
primeN = len(prime)
while True:
    if sectionSum == n:
        result += 1
        end+=1
        if end == primeN:
            break
        sectionSum += prime[end]
    elif sectionSum < n:
        end +=1
        if end == primeN:
            break
        sectionSum += prime[end]
    else :
        start+=1
        sectionSum -= prime[start]

print(result)