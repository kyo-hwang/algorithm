import sys
import collections

bNum = int(sys.stdin.readline().rstrip())
bHeights = [int(height) for height in sys.stdin.readline().rstrip().split(" ")]

stack = []

placesToSend = [0]*bNum

for i in range(len(bHeights)):
    #자기보다 작은 인덱스 버리기. 왜냐하면 가릴 것이기 때문에
    while stack and bHeights[stack[-1]] < bHeights[i]:
        stack.pop()

    #만약 스택이 비었다면 왼쪽에 자기보다 큰 값이 없다고 보면 된다. 즉 수정을 안해줘도 됨.
    if stack:
        #왼쪽에서 가장 높은 곳, 즉 레이저를 쏠 곳
        placesToSend[i] = stack[-1]+1

    stack.append(i)

print(*placesToSend)