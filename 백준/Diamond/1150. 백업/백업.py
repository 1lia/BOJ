import sys
import heapq

input = sys.stdin.readline
n, k = map(int,input().split())

nlist = []
visit = [0 for _ in range(n+1)]
dp = [0 for _ in range(n+1)]
h = []

result = 0
for _ in range(n):
    x = int(input())
    nlist.append(x)

if k*2 == n:
    for i in range(1,n,2):
        result += nlist[i] - nlist[i-1]

else:
    for i in range(1,n):
        heapq.heappush(h,[nlist[i] -nlist[i-1] , i - 1 , i])

    for i in range(k):
        a , b , c = heapq.heappop(h)
        while dp[b] == 1 or dp[c] == 1:
            a , b , c = heapq.heappop(h)

        result += a
        dp[b] , dp[c] = 1 , 1
        a = -a
        #c늘림
        if dp[c] == 1 and c < n-1:
            c += 1
            a += nlist[c] - nlist[c-1]
        #b늘림
        if dp[b] == 1 and b > 0:
            b -= 1
            a += nlist[b+1] - nlist[b]

        #오른쪽 겹치면
        count = 1
        while dp[c] == 1 and c < n-1:
            if count == 1:
                c += 1
                a -= nlist[c] - nlist[c-1]
                count = 0
            else:
                c += 1
                a += nlist[c] - nlist[c-1]
                count = 1

        #왼쪽 겹치면
        count = 1
        while dp[b] == 1 and b > 0:
            if count == 1:
                b -= 1
                a -= nlist[b+1] - nlist[b]
                count = 0
            else:
                b -= 1
                a += nlist[b+1] - nlist[b]
                count = 1
            
        heapq.heappush(h,[a, b ,c])
        
print(result)