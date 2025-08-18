class Main {
    public static void main(String[] args) throws Exception {
        long T = readLong();
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            int N = (int)readLong();
            long[] arr = new long[N + 1];
            long[] l = new long[N + 1];
            long[] r = new long[N + 1];
            for (int i = 1; i <= N; i++) {
                arr[i] = readLong();
            }

            for (int i = 1; i <= N; i++) {
                l[i] = arr[i] + l[i - 1];
            }

            for (int i = 1; i <= N; i++) {
                r[i] = arr[N - i + 1] + r[i - 1];
            }

            long res = 0;
            int max = (N >> 1) - 1;
            for (int i = 0; i <= max; i++) {
                res = Math.max(res , l[i] + r[max - i]);
            }
            sb.append(l[N] - res).append(' ').append(res).append('\n');
        }
        System.out.println(sb);
    }

    public static long readLong() throws Exception {
        long val = 0;
        long c = System.in.read();
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