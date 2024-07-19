import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        int N = readInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = readInt();
        }
        Arrays.sort(arr);
        int res = 0;
        for (int i = 1; i < N; i++) {
            int t = arr[i] - arr[0];
            res = gcd(res , t);
        }
        System.out.println(res);
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

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}