import java.util.ArrayList;
import java.util.Arrays;

class Main{
	public static ArrayList<ArrayList<Integer>> g;
	public static boolean[] visit;
	public static int[] match;
	public static void main(String[] args) throws Exception{
		g = new ArrayList<>();
		
		int N = readInt();
		int M = readInt();
		match = new int[M + 1];
		visit = new boolean[N + 1];
		
		for (int i = 0; i <= N; i++) {
			g.add(new ArrayList<>());
		}
		
		for (int i = 1; i <= N	; i++) {
			int a = readInt();
			for (int j = 0; j < a; j++) {
				g.get(i).add(readInt());
			}
		}
		int result = 0;
		for (int i = 1; i <= N; i++) {
			Arrays.fill(visit, false);
			if(dfs(i))
				result++;
		}
		System.out.println(result);	
	}
	
	public static boolean dfs(int num) {
		visit[num] = true;
		for (int i = 0; i < g.get(num).size(); i++) {
//			매칭가능하면 true return 안되면 다른거매칭되나 확인하고 바꿀수있으면 true
			if(match[g.get(num).get(i)] == 0 || (!visit[match[g.get(num).get(i)]] && dfs(match[g.get(num).get(i)]))) {
				match[g.get(num).get(i)] = num;
				return true;
			}
		}
		return false;
	}
	public static int readInt() throws Exception {
		int val = 0;
		boolean flag = false;
		do {
			int c = System.in.read();
			if (c == '-') {
				flag = true;
				continue;
			}
			if (c == 13)
				continue;

			if (c == 32 || c == 10)
				break;
			val = 10 * val + c - 48;
		} while (true);
		return flag ? -val : val;
	}
}