import sys
n = int(sys.stdin.readline())
nlist = list(map(int,sys.stdin.readline().split()))
dp = [nlist[0]]

for i in range(1,n):
   if dp[-1] < nlist[i]:
      dp.append(nlist[i])
   else:
      end = len(dp)
      start = 0
      mid = (start+end) // 2
      while True:
         if dp[mid] > nlist[i]:
            end = mid
            mid = (start+end) // 2
            if end == mid:
               dp[mid] = nlist[i]
               break
         elif dp[mid] <= nlist[i]:
            start = mid
            mid = (start+end) // 2
            if start == mid:
               if nlist[i] != dp[mid]:
                  dp[mid+1] = nlist[i]
               break

print(len(dp))