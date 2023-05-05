from fractions import Fraction
import math
N , M = map(int,input().split())
print(Fraction(math.factorial(N) , (math.factorial(M) * math.factorial(N-M))))