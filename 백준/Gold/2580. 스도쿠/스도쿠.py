import sys
nlist = [[0 for _ in range(9)] for _ in range(9)]

for i in range(9):
    nlist[i] = list(map(int,sys.stdin.readline().split()))

def check(x,y,n):
    for i in range(9):
        if nlist[x][i] == n:
            return False
        if nlist[i][y] == n:
            return False

    if x >= 0 and x <= 2:
        if y >= 0 and y <= 2:
            for i in range(3):
                for j in range(3):
                    if nlist[0+i][0+j] == n:
                        return False
        if y >= 3 and y <= 5:
            for i in range(3):
                for j in range(3):
                    if nlist[0+i][3+j] == n:
                        return False
        if y >= 6 and y <= 8:
            for i in range(3):
                for j in range(3):
                    if nlist[0+i][6+j] == n:
                        return False
    if x >= 3 and x <= 5:
        if y >= 0 and y <= 2:
            for i in range(3):
                for j in range(3):
                    if nlist[3+i][0+j] == n:
                        return False
        if y >= 3 and y <= 5:
            for i in range(3):
                for j in range(3):
                    if nlist[3+i][3+j] == n:
                        return False
        if y >= 6 and y <= 8:
            for i in range(3):
                for j in range(3):
                    if nlist[3+i][6+j] == n:
                        return False
    if x >= 6 and x <= 8:
        if y >= 0 and y <= 2:
            for i in range(3):
                for j in range(3):
                    if nlist[6+i][0+j] == n:
                        return False
        if y >= 3 and y <= 5:
            for i in range(3):
                for j in range(3):
                    if nlist[6+i][3+j] == n:
                        return False
        if y >= 6 and y <= 8:
            for i in range(3):
                for j in range(3):
                    if nlist[6+i][6+j] == n:
                        return False
    return True
    
def dfs(x,y,nlist): 
    if nlist[x][y] == 0:
        for i in range(1,10):
            if check(x,y,i):
                nlist[x][y] = i
                if x == 8 and y == 8:
                    for i in nlist:
                        print(' '.join(map(str,i)))
                    sys.exit(0)
                    return
                if y != 8:
                    dfs(x,y+1,nlist)
                else:
                    dfs(x+1,0,nlist)
                nlist[x][y] = 0
    else:
        if y != 8:
            dfs(x,y+1,nlist)
        else:
            if x == 8 and y == 8:
                for i in nlist:
                    print(' '.join(map(str,i)))
                sys.exit(0)
                return
            dfs(x+1,0,nlist)


dfs(0,0,nlist)