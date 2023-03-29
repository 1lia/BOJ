import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
	public static long MOD = 1000000007L;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
        	st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            long a = factorial(N);
            long b = factorial(M) * factorial(N-M) % MOD;
            System.out.println(a * solution(b,MOD - 2) % MOD);
		}
    }
    
    public static long factorial(int n) {
    	long result = 1;
    	for (long i = 2; i <= n; i++) {
			result = (result * i) % MOD;
		}
    	return result;
    }
    
    public static long solution(long n , long v) {
    	if(v == 0) {
    		return 1;
    	}
    	if(v == 1) {
    		return n;
    	}
    	
    	long temp = solution(n , v/2);
    	
    	if(v % 2 == 0)
    		return (temp * temp) % MOD;
    	else
    		return (((temp * temp) % MOD) * n) % MOD;
    }
}