import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	public static int result;
	public static ArrayList<Integer>[] graph;
	public static boolean[] visit;
	public static int[] match;
	public static void main(String[] args) throws Exception{
		int N = readInt();
		int K = readInt();
		visit = new boolean[N+1];
		match = new int[N+1];
		graph = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < K; i++) {
			graph[readInt()].add(readInt());
		}
		
		for (int i = 1; i <= N; i++) {
			Arrays.fill(visit, false);
			if(dfs(i)) {
				result++;
			}
		}
		System.out.println(result);
	}
	
	public static boolean dfs(int v) {
		visit[v] = true;
		for (int i = 0; i < graph[v].size(); i++) {
			int next = graph[v].get(i);
//			매칭이 안됬거나 아직 방문안했는데 돌려서 매칭이 되거나하면 연결해주고 한개 올릴 수 있음
			if(match[next] == 0 || (!visit[match[next]] && dfs(match[next]))) {
				match[next] = v;
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
            if(c == 13)
            	continue;
            
            if (c == 32 || c == 10) 
            	break;
            val = 10 * val + c - 48;
        } while (true);
        return flag ? -val : val;
    }
}

