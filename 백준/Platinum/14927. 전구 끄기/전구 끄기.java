import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int[] map;
	public static int result = Integer.MAX_VALUE;
	public static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				if(Integer.parseInt(st.nextToken()) == 1)
					map[i] |= 1 << j;
			}
		}
		
		for (int i = 0; i < (1 << N); i++) {
			dfs(2 , map[0] ^ i ^ (i << 1) ^ (i >> 1), Integer.bitCount(i) , map[1] ^ i);
		}
		if(result == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(result);

	}
	
	public static void dfs(int depth , int temp , int count , int next) {
		if(temp >= (1 << N))
			temp ^= (1 << N);

		count += Integer.bitCount(temp);
		if(result < count)
			return;
		if(depth == N) {
			int t = next ^ temp ^ (temp << 1) ^ (temp >> 1);
			if(t == 0 || t == (1 << N)) {
				result = count;
			}
		} else {
			dfs(depth + 1 , next ^ temp ^ (temp << 1) ^ (temp >> 1), count , map[depth] ^ temp);
		}	
	}
}