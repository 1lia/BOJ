import sys
from collections import deque
input = sys.stdin.readline

def bfs():
   a_group = []
   b_group = []

   for i in range(1,V+1):
      if visit[i] == 0:
         que.append(i)
         visit[i] = 1
      while que:
         a = que.popleft()          
         if a in dic: 
            for z in dic[a]:
               if visit[z] == 0:
                  if visit[a] == 1:
                     visit[z] = 2
                  else:
                     visit[z] = 1
                  que.append(z)
               elif visit[z] == visit[a]:
                  print('NO')
                  return    
         
   print('YES')
   return


K = int(input())
for _ in range(K):
   V , E = map(int,input().split())
   nlist = []
   visit = [0 for _ in range(V+1)]
   dic = {}
   que = deque()
   for _ in range(E):
      u , v = map(int,input().split())
      if u in dic:
         dic[u].append(v)
      else:
         dic[u] = [v]
      if v in dic:
         dic[v].append(u)
      else:
         dic[v] = [u]
   
   bfs()
