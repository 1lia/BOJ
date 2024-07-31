class Main {
    public static void main(String[] args) throws Exception {
        int N = (int) readLong();
        long[] arr = new long[N];
        long l = 0;
        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum += arr[i] = readLong();
            l += arr[i] / 2;
        }
        if(sum % 3 == 0 && l >= sum / 3){
            System.out.println("YES");
        } else{
            System.out.println("NO");
        }
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