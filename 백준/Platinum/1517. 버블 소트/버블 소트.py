'''import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

def merge(left,mid,right):
   global count
   temp = []
   l , h = left,mid+1

   while l <= mid and h <= right:
      if nlist[l] <= nlist[h]:
         temp.append(nlist[l])
         l += 1
      else:
         temp.append(nlist[h])
         h += 1
         count += (mid - l + 1)

   if l <= mid:
      temp = temp + nlist[l:mid+1]
   if h <= right:
      temp = temp + nlist[h:right+1]

   for i in range(len(temp)):
      nlist[left + i] = temp[i]

def merge_sort(left,right):
   if left < right:
      mid = (left+right) // 2
      merge_sort(left,mid)
      merge_sort(mid+1,right)
      merge(left,mid,right)

n = int(input())
nlist = list(map(int,input().split()))
count = 0
merge_sort(0,n-1)
print(nlist)
print(count)
'''
import sys

read = lambda: sys.stdin.readline().rstrip()

def merge_sort(start, end):
    global swap_count, A

    if start < end:
        mid = (start + end) // 2
        merge_sort(start, mid)
        merge_sort(mid + 1, end)

        a, b = start, mid + 1
        temp = []

        while a <= mid and b <= end:
            if A[a] <= A[b]:
                temp.append(A[a])
                a += 1
            else:
                temp.append(A[b])
                b += 1
                swap_count += (mid - a + 1)

        if a <= mid:
            temp = temp + A[a:mid + 1]
        if b <= end:
            temp = temp + A[b:end + 1]

        for i in range(len(temp)):
            A[start + i] = temp[i]


swap_count = 0
N = int(read())
A = list(map(int, read().split()))
merge_sort(0, N - 1)
print(swap_count)
