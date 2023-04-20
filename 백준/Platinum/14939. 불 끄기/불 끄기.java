import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static int[] map;
	public static int result = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[10];
		for (int i = 0; i < 10; i++) {
			String s = br.readLine();
			for (int j = 0; j < 10; j++) {
				if(s.charAt(j) == 'O')
					map[i] |= 1 << j;
			}
		}
		
		for (int i = 0; i < 1024; i++) {
			dfs(2 , map[0] ^ i ^ (i << 1) ^ (i >> 1), Integer.bitCount(i) , map[1] ^ i);
		}
		if(result == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(result);

	}
	
	public static void dfs(int depth , int temp , int count , int next) {
		if(temp >= (1 << 10))
			temp ^= (1 << 10);

		if(result < count)
			return;
		if(depth == 10) {
			count += Integer.bitCount(temp);
			int t = next ^ temp ^ (temp << 1) ^ (temp >> 1);
			if(t == 0 || t == 1024)
				result = count;
		} else {
			int nw = map[depth] ^ temp;
			count += Integer.bitCount(temp);
			dfs(depth + 1 , next ^ temp ^ (temp << 1) ^ (temp >> 1), count , nw);
		}	
	}
}