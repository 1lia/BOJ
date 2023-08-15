import sys
input = sys.stdin.readline
n = int(input())
def sol(order, s):
    if order == "all":
        tmp = [i for i in range(1, 20+1)]
        s = set(tmp)
    elif order == "empty":
        s = set([])
    else:
        odr, num = map(str, order.split())
        if odr == "add" and int(num) not in s:
            s.add(int(num))
        elif odr == "remove":
            if len(s) > 0 and int(num) in s:
                s.remove(int(num))
        elif odr == "check":
            if int(num) in s:
                print(1)
            else:
                print(0)
        else:
            if int(num) in s:
                s.remove(int(num))
            else:
                s.add(int(num))
    return s
s = set([])
for i in range(n):
    order = input().strip()
    s = sol(order, s)