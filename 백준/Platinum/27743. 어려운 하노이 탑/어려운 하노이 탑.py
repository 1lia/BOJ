q = 1000000007
def asd(n):
   if(n==1):
      return 2

   p = asd(n//2)%q
   if(n%2==1):
      return (p * p * 2)
   else:
      return (p * p)

N , M = map(int,input().split())
if(M == 1):
   a = asd(N)-1
else:
   a = asd(N+1)*M-M-M-1
print(a%q)