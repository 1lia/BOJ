import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        int N = (int) readLong();
        long R = readLong();
        long M = readLong();
        long res = 0;
        long[] arr = new long[N + 1];
        long[] arr2 = new long[(N << 1)];
        for (int i = 0; i < N; i++) {
            arr[i] = readLong();
        }
        Arrays.sort(arr , 0 , N);
        arr[N] = arr[0] + M;
        for (int i = 0; i < N; i++) {
            arr2[i] = arr2[i + N] = arr[i + 1] - arr[i] - R - R;
        }
        long sum = 0;
        for (int i = 0; i + 1 < arr2.length; i++) {
            sum += arr2[i];
            if(sum < 0)
                sum = 0;
            res = Math.max(res , sum);
        }
        System.out.println((res + 1) >> 1);
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