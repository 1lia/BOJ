import sys
input = sys.stdin.readline
N = int(input())
nlist = list(map(int,input().split()))
   
dp = [[] for _ in range(N)]
count = N

for i in range(len(nlist)):
   dp[i] = [nlist[i]]

for i in range(1,N):
   for p in dp[i-1]:
      if p == nlist[i]:
         dp[i] = []
         count -= 1
         break
      else:
         if nlist[i] > p:
            dp[i].append(nlist[i]-p)
print(count)
