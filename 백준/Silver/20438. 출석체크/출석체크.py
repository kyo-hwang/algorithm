import sys
# sys.stdin = open("input.txt","r")

studentN,stduentSleepN,studentSendCodeN,intevralN = map(int,sys.stdin.readline().split())

sleepStudents = list(map(int,sys.stdin.readline().split()))
studentsToSend = list(map(int,sys.stdin.readline().split()))
intervals = [list(map(int,sys.stdin.readline().split())) for i in range(intevralN)]

last = studentN+3
sleepStudentsTable = [False for i in range(last)]
for sleepStudent in sleepStudents:
    sleepStudentsTable[sleepStudent] = True

attendence = [False for i in range(last)]

for studentToSend in studentsToSend:
    if not sleepStudentsTable[studentToSend]:
        for i in range(studentToSend,last,studentToSend):
            attendence[i] = True

for studentSleep in sleepStudents:
    attendence[studentSleep] = False

for start,end in intervals:
    result = 0
    for i in range(start,end+1):
        if not attendence[i]:
            result+=1
    print(result)
