import sys
import collections
# sys.stdin = open("input.txt","r")

bSize = int(sys.stdin.readline().rstrip())

aNum = int(sys.stdin.readline().rstrip())
aLocs = []
for i in range(aNum):
    aLocs.append((int(i)for i in sys.stdin.readline().rstrip().split(" ")))

directionChangeNum = int(sys.stdin.readline().rstrip())
directionChanges = collections.deque()
for i in range(directionChangeNum):
    when, where = ((i)for i in sys.stdin.readline().rstrip().split(" "))
    directionChanges.append((int(when),where))

board = [[False for col in range(bSize)] for row in range(bSize)]

#사과가 존재하는지에 대한 값
appleBoard = [[False for col in range(bSize)] for row in range(bSize)]
for aRow,aCol in aLocs:
    appleBoard[aRow-1][aCol-1] = True

def getDirection(flag_d):
    dCol = [0,1,0,-1]
    dRow = [-1,0,1,0]

    return dCol[flag_d],dRow[flag_d]

headRow =0
headCol =0

#과거의 위치를 추적하는 queue생성
history = collections.deque([(headRow,headCol)])

time = 0

board[0][0] = True

#처음방향 설정
flag_d = 1

#방향 언제 바꿀지와 바꾸는 방향 설정
when,where = directionChanges.popleft()

while(True):
    #시간증가
    time += 1
    #방향설정
    dCol,dRow = getDirection(flag_d)
    #머리 내밀기
    headCol+=dCol
    headRow+=dRow
    history.append((headRow,headCol))

    #머리가 넘어가는지 검사
    if headCol >=bSize or headCol< 0 or headRow>=bSize or headRow<0:
        break
    #머리 내밀었을때 자기 몸에 닿는지
    if board[headRow][headCol]:
        break

    #안 닿으면 머리 추가
    board[headRow][headCol] = True

    #사과를 먹으면 꼬리 유지, 아니면 꼬리 제거
    if not appleBoard[headRow][headCol] :
        his = history.popleft()
        bRow,bCol = his
        board[bRow][bCol] =False
    else:
        appleBoard[headRow][headCol] = False

    #방향 바꾸기
    if time == when:
        if where == "D":
            flag_d = (flag_d+1)%4
        elif where == "L":
            flag_d = (4+flag_d-1)%4
        if directionChanges:
            when,where = directionChanges.popleft()

print(time)
    