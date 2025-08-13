class Main {
    static final long MOD = 9_999_991L;
    static final int MAX = 251; // N+M+1 최대
    static long[] fact = new long[MAX + 1];
    static long[] ifact = new long[MAX + 1];
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        buildFact();
        int N = readInt();
        for (int i = 0; i < N; i++) {
            int a = readInt();
            int b = readInt();
            sb.append(solve(a,b)).append('\n');
        }
        System.out.println(sb);
    }
    static long modPow(long a, long e) {
        long r = 1;
        while (e > 0) {
            if ((e & 1) == 1) r = (r * a) % MOD;
            a = (a * a) % MOD;
            e >>= 1;
        }
        return r;
    }

    static void buildFact() {
        fact[0] = 1;
        for (int i = 1; i <= MAX; i++) fact[i] = (fact[i - 1] * i) % MOD;
        ifact[MAX] = modPow(fact[MAX], MOD - 2);
        for (int i = MAX; i > 0; i--) ifact[i - 1] = (ifact[i] * i) % MOD;
    }

    static long nCr(int n, int r) {
        if (r < 0 || r > n) return 0;
        return (((fact[n] * ifact[r]) % MOD) * ifact[n - r]) % MOD;
    }

    static long solve(int N, int M) {
        int S = N + M;
        long a = nCr(S, N);
        long b = nCr(S + 1, N);
        long inv = modPow(N + 1, MOD - 2);
        return (((a * b) % MOD) * inv) % MOD;
    }

    public static int readInt() throws Exception {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        boolean flag = (c == '-');
        if (flag)
            c = System.in.read();
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);

        if (flag)
            return -val;
        return val;
    }
}