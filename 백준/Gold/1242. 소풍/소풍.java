class Main {
    public static void main(String[] args) throws Exception {
        int N = readInt();
        int K = readInt();
        int M = readInt();

        for (int i = N; i > 0; i--) {
            M -= (((K - 1) % i) + 1);
            if(M < 0){
                M += i;
            }

            if(M == 0){
                System.out.println(N - i + 1);
                return;
            }
        }
        System.out.println(N);
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