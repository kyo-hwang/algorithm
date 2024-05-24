import sys
# sys.stdin = open("input.txt","r")

n = int(sys.stdin.readline())

words = [sys.stdin.readline().rstrip() for _ in range(n)]

chPersists = set()

for word in words:
    for ch in word:
        chPersists.add(ch)

chValue = dict().fromkeys(chPersists,0)

for word in words:
    for i in range(len(word)):
        chValue[word[i]] += 10**(len(word)-1-i)
order = sorted(chValue.items(),key= lambda x:-x[1])

numBych = dict()
value = 9
for ch in order:
    numBych[ch[0]] = str(value)
    value-=1

result = 0
for word in words:
    tempNum = str()
    for ch in word:
        tempNum+=numBych[ch]
    result += int(tempNum)

print(result)