import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        long N = readLong();
        long K = readLong();
        Queue<Long> q = new ArrayDeque<>();
        Deque<Integer> q2 = new ArrayDeque<>();
        long res = 0;
        int fi = 0;
        
        while(true){
            long t = (N + 1 - fi) >> 1;
            if(t >= K || K == 1){
                res = ((K - 1) << 1) + 1 + fi;
                if(N == 1)
                    res = 1;
                break;
            }
            q.offer(t);
            q2.offer(fi);
            if(((N + fi) & 1) == 0){
                fi = 0;
            } else{
                fi = 1;
            }

            K -= t;
            N -= t;
        }
        while(!q2.isEmpty()){
            int f = q2.pollLast();
            if(f == 0){
                res <<= 1;
            } else{
                res += (res - 1);
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
