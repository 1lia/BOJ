import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		long N = Long.parseLong(bf.readLine());
		long result = N;
		
		for (long i = 2; i * i <= N; i++) {
			if(N % i == 0) {
				result = result / i * (i-1);			
			}
			
			while(N % i == 0) {
				N /= i;
			}
		}

		if(N == 1) {
			System.out.println(result);
		} else {
			System.out.println(result / N * (N-1));
		}
	}
}