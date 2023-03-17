import sys

N = int(sys.stdin.readline())
nlist = []
result = sys.maxsize

for _ in range(N):
    r , g , b = map(int,sys.stdin.readline().split())
    nlist.append([r,g,b])


for j in range(3):
   dp = [[sys.maxsize for i in range(3)] for _ in range(N)]
   dp[0][j] = nlist[0][j]

   for i in range(1,N):
      dp[i][0] = nlist[i][0] + min(dp[i-1][1] , dp[i-1][2])
      dp[i][1] = nlist[i][1] + min(dp[i-1][0] , dp[i-1][2])
      dp[i][2] = nlist[i][2] + min(dp[i-1][0] , dp[i-1][1])

   for i in range(3):
      if i != j:
         result = min(result,dp[-1][i])

print(result)