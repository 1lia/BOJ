import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		if(a + b == 0) {
			if(c == 0) {
				System.out.println(d);
			} else {
				System.out.println(d + 1);
			}
		} else {
			if(b == 0) {
				System.out.println(a);
			} else {
				if(c == 0) {
					System.out.println(a + d + 1);
				} else {
					if(b < c && d > 0) {
						System.out.println(a + b + b + d);
					} else if(b < c && d == 0) {
						System.out.println(a + b + b);
					} else if(b == c) {
						System.out.println(a + b + c + d);
					} else if(b > c) {
						System.out.println(a + c + c + d + 1);
					}

				}
			}
		}
	}
}