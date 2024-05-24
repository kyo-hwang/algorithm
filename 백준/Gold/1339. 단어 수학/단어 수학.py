import sys
# sys.stdin = open("input.txt","r")

n = int(sys.stdin.readline())

words = []
maxlen = -sys.maxsize
misteryNums = set()
for _ in range(n):
    word = sys.stdin.readline().rstrip()[::-1]
    words.append(word)
    for ch in word:
        misteryNums.add(ch)
    maxlen = max(maxlen,len(word))

wordCost = dict().fromkeys(misteryNums,0)
possibleMaxNum = 9

for i in range(maxlen-1,-1,-1):
    # print(misteryNums)
    for word in words:
        if len(word)>i:
            wordCost[word[i]] += 10**i

wordCostSort = []
for ch in wordCost.keys():
    wordCostSort.append((ch,wordCost[ch]))

wordCostSort.sort(key=lambda x:-x[1])
numByCh = dict().fromkeys(misteryNums,0)

for ch,cost in wordCostSort:
    numByCh[ch] = str(possibleMaxNum)
    possibleMaxNum -=1

def wordToNum(numByCh,word):
    numString = ""
    for ch in word:
        numString+=numByCh[ch]
    return int(numString)

result = 0
for word in words:
    result += wordToNum(numByCh,word[::-1])

print(result) 