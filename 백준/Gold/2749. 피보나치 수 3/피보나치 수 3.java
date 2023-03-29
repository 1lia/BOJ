import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(bf.readLine());
		System.out.println(fibo(N)[0][1]);
	}

	public static long[][] fibo(long num) {
		if  (num == 1) {
			long[][] a = {{0,1},{1,1}};
			return a;
		}
		long[][] temp = fibo(num/2);
		
		if(num % 2 == 0)
			return multi(temp , temp);
		else
			return multi(multi(temp , temp) , fibo(1));
	}
	
	public static long[][] multi(long[][] arr1 , long[][] arr2) {
		long[][] arr3 = new long[2][2];
		
		arr3[0][0] = (arr1[0][0] * arr2[0][0] + arr1[0][1] * arr2[1][0]) % 1000000;
		arr3[0][1] = (arr1[0][0] * arr2[0][1] + arr1[0][1] * arr2[1][1]) % 1000000;
		arr3[1][0] = (arr1[1][0] * arr2[0][0] + arr1[1][1] * arr2[1][0]) % 1000000;
		arr3[1][1] = (arr1[1][0] * arr2[0][1] + arr1[1][1] * arr2[1][1]) % 1000000;
		
		return arr3;
	}
}