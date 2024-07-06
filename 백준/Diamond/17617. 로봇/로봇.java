import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        int N = readInt();
        long R = readLong();
        long M = readLong();
        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = readLong();
        }
        Arrays.sort(arr);
        long res = M - arr[N - 1] + arr[0];
        for (int i = 1; i < N; i++) {
            res = Math.max(res , arr[i] - arr[i - 1]);
        }
        res -= (R + R);
        if(res <= 0)
            System.out.println(0);
        else System.out.println((res + 1) >> 1);
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