import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        int N = (int)readLong();
        Node[] arr = new Node[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new Node();
            arr[i].x = readLong();
            arr[i].y = readLong();
            arr[i].c = arr[i].x - arr[i].y;
        }

        int q = (int)readLong();
        long xc = 0;
        long yc = 0;
        Arrays.sort(arr);
        long[] sumX = new long[N];
        long[] sumY = new long[N];
        sumX[0] = arr[0].x;
        sumY[0] = arr[0].y;
        for (int i = 1; i < N; i++) {
            sumX[i] = sumX[i - 1] + arr[i].x;
            sumY[i] = sumY[i - 1] + arr[i].y;
        }
        for (int i = 0; i < q; i++) {
            if(readLong() == 17){
                xc += readLong();
            } else {
                yc += readLong();
            }
            int s = 0;
            int e = N - 1;
            long val = yc - xc;
            if(val >= arr[N - 1].c){
                sb.append(sumY[N - 1] + N * yc).append('\n');
            } else {
                while(s < e){
                    int m = (s + e) >> 1;
                    if(arr[m].c >= val){
                        e = m;
                    } else{
                        s = m + 1;
                    }
                }
                if(e == 0){
                    sb.append(sumX[N - 1] + N * xc).append('\n');
                } else{
                    sb.append(sumY[e - 1] + e * yc + sumX[N - 1] - sumX[e - 1] + (N - e) * xc).append('\n');
                }
            }
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
    static class Node implements Comparable<Node>{
        long x;
        long y;
        long c;

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.c, o.c);
        }
    }
}
