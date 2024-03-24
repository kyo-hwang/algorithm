n = int(input())

global result
result = 0

flag_a = [False]*n
flag_b = [False]*(2*n-1)
flag_c = [False]*(2*n-1)

def n_queen(i):
    global result
    if(i==n):
        result +=1
        return
    for j in range(0,n):
        if(not (flag_a[j] or flag_b[i+j] or flag_c[j+n-1-i]) ):
            flag_a[j] = True
            flag_b[i+j] = True
            flag_c[j+n-1-i] = True
            n_queen(i+1)
            flag_a[j] = False
            flag_b[i+j] = False
            flag_c[j+n-1-i] = False

n_queen(0)

print(result)