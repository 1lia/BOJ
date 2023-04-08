import sys
input = sys.stdin.readline

def find(n):
    result = 0
    for i in range(1,int(n**(1/2))+1):
        result += dp[i] * (n // (i*i))
    return result
    
K = int(input())
start , end = 0,2000000000
dp = [0 for i in range(1000001)]
dp[1] = 1

for i in range(1, 1000001):
    if dp[i]:
        for j in range(i * 2, 1000001, i):
            dp[j] -= dp[i]
            
while start < end-1:
    mid = (start + end) // 2
    if find(mid) < K:
        start = mid
    else:
        end = mid

print(end)