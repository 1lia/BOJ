import sys
st = []

while True:
   nlist = list(map(int,sys.stdin.readline().split()))
   if len(nlist) == 1 and nlist[0] == 0:
      break

   N = nlist[0]
   result = [0 for i in range(N)]
   nlist = nlist[1:]
   st.clear()

   for i in range(N):
      if len(st) == 0:
         st.append(i)

      elif nlist[st[-1]] <= nlist[i]:
         st.append(i)
      elif nlist[st[-1]] > nlist[i]:
         while True:
            if len(st) == 0:
               break
            if nlist[st[-1]] <= nlist[i]:
               break
            if len(st) == 1:
               x = st.pop()
               result[x] = nlist[x] * (i)
            else:
               x = st.pop()
               result[x] = nlist[x] * (i-st[-1]-1)
         st.append(i)

   while True:
      if len(st) == 0:
         break
      if len(st) == 1:
         x = st.pop()
         result[x] = nlist[x] * (N)
      else:
         x = st.pop()
         result[x] = nlist[x] * (N-st[-1]-1)

   print(max(result))

