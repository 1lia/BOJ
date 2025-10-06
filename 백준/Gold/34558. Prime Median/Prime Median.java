import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        ArrayList<Integer> prime;
        int[] spf = new int[1000001];
        int[] idx = new int[1000001];
        prime = new ArrayList<>();

        for (int i = 2; i <= 1000000; i++) {
            if(spf[i] == 0) {
                prime.add(i);
                spf[i] = i;
                idx[i] = 1;
            }

            for (int j = 0; j < prime.size(); j++) {
                int p = prime.get(j);
                if(i * prime.get(j) > 1000000) break;
                spf[i*p] = p;
                if(i % p == 0) break;
            }
        }
        idx[0] = -1;
        for (int i = 1; i <= 1000000; i++) {
            idx[i] += idx[i - 1];
        }

        StringBuilder sb = new StringBuilder();
        int N = readInt();
        for (int i = 0; i < N; i++) {
            int s = idx[readInt() - 1] + 1;
            int e = idx[readInt()];
            int m = e - s;
            if(0 <= m && (m & 1) == 0){
                sb.append(prime.get((s + e) >> 1));
            } else{
                sb.append(-1);
            }
            sb.append('\n');
        }
        System.out.println(sb);
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