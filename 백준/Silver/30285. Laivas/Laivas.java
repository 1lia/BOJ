class Main {
    public static void main(String[] args) throws Exception {
        int a = readInt();
        int b = readInt();
        int c = readInt();
        int d = readInt();
        int N = readInt();

        int res = 0;
        int len = a / c;
        int cnt1 = b / d;
        int cnt2 = b / c;
        for (int i = 0; i <= len ; i++) {
            res = Math.max(res ,(((a - (c * i)) / d) * cnt2) + (cnt1 * i));
        }
        cnt1 = a / c;
        cnt2 = a / d;
        len = b / c;

        for (int i = 0; i <= len ; i++) {
            res = Math.max(res ,(((b - (c * i)) / d) * cnt1) + (cnt2 * i));
        }
        if(res >= N){
            System.out.println("TAIP");
        } else{
            System.out.println(res);
        }

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