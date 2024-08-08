import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        long res = 0;
        int N = (int) readLong();
        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = readLong();
        }
        Arrays.sort(arr);
        long t = 0;
        long mul = 0;
        for (int i = N - 1; i >= 0; i--) {
            if(t == 0){
                t = arr[i];
            } else if(t == arr[i] || t - 1 == arr[i]){
                if(mul == 0){
                    mul = arr[i];
                } else{
                    res += mul * arr[i];
                    mul = 0;
                }
                t = 0;
            } else{
                t = arr[i];
            }
        }
        System.out.println(res);
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