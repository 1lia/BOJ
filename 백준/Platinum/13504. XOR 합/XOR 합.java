import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        int T = readInt();
        for (int z = 0; z < T; z++) {
            int N = readInt();
            long[] arr = new long[N];
            long[] xor = new long[N];
            for (int i = 0; i < N; i++) {
                arr[i] = readLong();
            }
            xor[0] = arr[0];
            long max = xor[0];
            for (int i = 1; i < N; i++) {
                xor[i] = xor[i-1] ^ arr[i];
                max = Math.max(xor[i] , max);
            }
            long size = 30;
            while(size > 0){
                if(((1 << size) & max) != 0){
                    break;
                }
                size--;
            }

            Trie trie = new Trie();
            trie.size = size;
            for (int i = 0; i < N; i++) {
                trie.insert(xor[i]);
            }

            long res = max;
            for (int i = 0; i < N; i++) {
                res = Math.max(trie.query(xor[i]) , res);
            }
            sb.append(res).append('\n');
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
        if (flag) c = System.in.read();
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);

        if (flag) return -val;
        return val;
    }

    public static long readLong() throws Exception {
        long val = 0;
        long c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        boolean flag = (c == '-');
        if (flag) c = System.in.read();
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);

        if (flag) return -val;
        return val;
    }
}

class Trie{
    private int[][] trie = new int[10000000][2];
    private int piv;
    public long size;
    public void insert(long n){
        int p = 0;
        int t = 0;

        for (long i = size; i >= 0; i--) {
            if(((1 << i) & n) == 0){
                t = 0;
            } else{
                t = 1;
            }
            if(trie[p][t] == 0){
                trie[p][t] = ++piv;
            }
            p = trie[p][t];
        }
    }
    public long query(long n){
        int p = 0;
        int t = 0;
        long tmp = 0;
        for (long i = size; i >= 0; i--) {
            if(((1 << i) & n) == 0){
                t = 1;
            } else{
                t = 0;
            }

            if(trie[p][t] != 0){
                p = trie[p][t];
            } else{
                if(t == 1){
                    p = trie[p][0];
                } else{
                    p = trie[p][1];
                }
                t ^= 1;
            }
            tmp |= (t << i);
        }

        return n ^ tmp;
    }
}