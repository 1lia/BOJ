import sys
input = sys.stdin.readline
import math
dddd = 1000000007
sys.setrecursionlimit(10**6)

def seg(start,end,node):
   if start == end:
      tree[node] = nlist[start]
      return tree[node]

   mid = (start + end) // 2
   tree[node] = (seg(start,mid,node * 2) * seg(mid+1,end,node*2+1)) % dddd
   return tree[node]

def sumtree(start,end,node,left,right):
   result = 0
   #범위밖
   if left > end or right < start:
      return 1
   if left <= start and end <= right:
      return tree[node]

   mid = (start + end) // 2
   result = (sumtree(start,mid,node * 2 , left, right) * sumtree(mid+1,end,node * 2 + 1 , left, right)) % dddd
   return result

def update(start,end,node,index,old,new):
   #현재범위 밖에있음
   if index > end or index < start:
      return

   #수정
   
   if start == end:
      tree[node] = new
      return
   mid = (start + end) // 2
   update(start,mid,node*2,index,old,new)
   update(mid+1,end,node*2+1,index,old,new)
   tree[node] = tree[2*node] * tree[2*node+1] % dddd
   
      
   #끝이면 종료
   
   

n , m , k = map(int,input().split())
nlist = [0]
tree = [0 for i in range(2**int(math.log2(n)+1)*2)]

for i in range(1,n+1):
   a = int(input())
   nlist.append(a)

seg(1,n,1)
for _ in range(m+k):
   a , b , c = map(int,input().split())
   if a == 1:
      temp = nlist[b]
      nlist[b] = c
      update(1,n,1,b,temp,c)
   elif a == 2:
      print(sumtree(1,n,1,b,c))
   

   
