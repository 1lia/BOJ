import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

def dfs(start,a):
   if a in dic:
      for i in range(len(dic[a])):
         if dp[dic[a][i][0]] == 0 and dic[a][i][0] != start:
            dp[dic[a][i][0]] = dp[a] + dic[a][i][1]
            dfs(start,dic[a][i][0])
   return

t = int(input())
dp = [0 for _ in range(t+1)]
dic = {}
for _ in range(t-1):
   nlist = list(map(int,input().split()))
   if nlist[0] in dic:
      dic[nlist[0]].append((nlist[1] , nlist[2]))
   else:
      dic[nlist[0]] = [(nlist[1] , nlist[2])]

   if nlist[1] in dic:
      dic[nlist[1]].append((nlist[0] , nlist[2]))
   else:
      dic[nlist[1]] = [(nlist[0] , nlist[2])]

dfs(1,1)
a = max(dp)
start = dp.index(a)
dp = [0 for _ in range(t+1)]
dfs(start,start)
print(max(dp))
