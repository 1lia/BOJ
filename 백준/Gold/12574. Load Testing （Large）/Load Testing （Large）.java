class Main {
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        long T = readLong();
        for (long z = 1; z <= T; z++) {
            long L = readLong();
            long P = readLong();
            long C = readLong();
            int cnt = 1;
            while(L * C < P){
                cnt++;
                L *= C;
            }
            int res = 0;
            int check = 1;
            while(check < cnt){
                res++;
                check <<= 1;
            }
            sb.append("Case #").append(z).append(": ").append(res).append('\n');
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