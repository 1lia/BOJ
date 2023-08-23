import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	public static long[] arr;
	public static long[] sum;
	public static long[] dp;
    public static void main(String[] args) throws Exception {
        int N = (int) readLong();
        int K = (int) readLong();
        arr = new long[N+1];
        sum = new long[N+1];
        dp = new long[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = readLong();
        }

        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i-1] + arr[i];
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if(!q.isEmpty() && q.peekFirst() < i - K){
                q.pollFirst();
            }
            while(!q.isEmpty() && max(q.peekLast()) <= max(i)){
                q.pollLast();
            }
            q.offerLast(i);
            
            if(i <= K){
                dp[i] = sum[i];
            } else{
            	dp[i] = sum[i] + max(q.peekFirst());
            }
        }
        System.out.println(dp[N]);
    }
    
    public static long max(int idx) {
    	return dp[idx - 1] - sum[idx];
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