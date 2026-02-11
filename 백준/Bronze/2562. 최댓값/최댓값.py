import sys

a = []

for i in range(9):
    x = sys.stdin.readline()
    a.append(int(x))
    
max = a[0]
c = 1

for i in a:
    if max < i:
        max = i
        c = a.index(i) + 1

print(max)
print(c)