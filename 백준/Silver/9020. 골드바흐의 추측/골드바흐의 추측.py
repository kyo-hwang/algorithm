import sys

def decidePrime(isNumPrime):
    for i in range(2,len(isNumPrime)):
        for j in range(2,int(i**0.5)+1):
            if(i%j==0):
                isNumPrime[i] = False
                break
            

numOfTestCase = int(sys.stdin.readline().strip())

## 각 테스트 케이스 숫자 입력받기
inputNums = []

for i in range(0,numOfTestCase):
    inputNums.append(int(sys.stdin.readline().strip()))


# 입력 중 가장 큰 값보다 작은 소수들 찾기
isNumPrime = [True]*((max(inputNums))+1)

isNumPrime[0], isNumPrime[1] = False,False

decidePrime(isNumPrime)


for inputNum in inputNums:
    a,b = inputNum//2,inputNum//2
    while a>0 :
        if isNumPrime[a] and isNumPrime[b] :
            print(a,b)
            break

        else :
            a -=1
            b +=1