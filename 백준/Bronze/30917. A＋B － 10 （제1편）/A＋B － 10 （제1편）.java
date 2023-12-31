import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder t = new StringBuilder();
		StringBuilder res = new StringBuilder();
		int A = 0;
		int B = 0;

		for (int i = 1; i <= 9; i++) {
			t.setLength(0);
			System.out.println(t.append("? A ").append(i));	
			if(Integer.parseInt(br.readLine()) == 1) {
				A = i;
			}
		}
		
		for (int i = 1; i <= 9; i++) {
			t.setLength(0);
			System.out.println(t.append("? B ").append(i));	
			if(Integer.parseInt(br.readLine()) == 1) {
				B = i;
			}
		}

		res.append("! ").append(A + B);
		System.out.println(res);
	}
}