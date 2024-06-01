import sys
import copy
# sys.stdin = open("input.txt","r")

n  = int(sys.stdin.readline())

graph = []

for i in range(n):
    graph.append(list(map(int,sys.stdin.readline().split())))

def getTeamScore(graph,team):
    if not team:
        return 0
    score = 0
    for member in team[1:]:
        # print(member)
        score += (graph[team[0]][member]+graph[member][team[0]])
    return score + getTeamScore(graph,team[1:])

def getTeamScoreDis(graph,team1):
    global n
    team2 = list(range(n))
    for member in team1:
        team2.remove(member)
    return abs(getTeamScore(graph,team1)-getTeamScore(graph,team2))

result = sys.maxsize
def backTracking(index,graph,team):
    global n
    global result
    if index == n:
        return
    if len(team)==n//2:
        result = min(getTeamScoreDis(graph,team),result)
        return
    backTracking(index+1,graph,copy.deepcopy(team))
    team.append(index)
    backTracking(index+1,graph,team)

backTracking(0,graph,[])

print(result)
