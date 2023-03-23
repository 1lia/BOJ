import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		Node[] node = new Node[((N * (N-1)) >> 1)];
		Pos[] pos = new Pos[N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			pos[i] = new Pos(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
		}
		
		int cnt = 0;
		for (int i = 1; i < N; i++) {
			for (int j = i + 1; j <= N; j++) {
				node[cnt++] = new Node(i,j,Math.sqrt(Math.pow(pos[i].x - pos[j].x, 2) + Math.pow(pos[i].y - pos[j].y, 2)));
			}
		}
		Arrays.sort(node);
		UnionFind uf = new UnionFind(N);
		double result = 0;
		cnt = 0;
		for (int i = 0; i < node.length; i++) {
			if(!uf.Union(node[i].start, node[i].end)) {
				uf.merge(node[i].start, node[i].end);
				result += node[i].w;
				cnt++;
			}
			if(cnt == N - 1)
				break;
		}
		System.out.println(result);
	}
}
class Pos{
	double x;
	double y;
	public Pos(double x, double y) {
		this.x = x;
		this.y = y;
	}
}

class Node implements Comparable<Node>{
	int start;
	int end;
	double w;
	
	Node(int start , int end , double w){
		this.start = start;
		this.end = end;
		this.w = w;
	}
	@Override
	public int compareTo(Node o) {
		if(this.w < o.w)
			return -1;
		return 1;
	}
}

class UnionFind{
	int[] arr;
	
	UnionFind(int n){
		arr = new int[n+1];
		init();
	}

	private void init() {
		for (int i = 1; i < arr.length; i++) {
			arr[i] = i;
		}	
	}
	public int find(int x) {
		if(arr[x] == x)
			return x;
		return arr[x] = find(arr[x]);
	}
	
	public void merge(int x , int y) {
		x = find(x);
		y = find(y);
		
		if(x == y)
			return;
		arr[y] = x;
		return;	
	}
	public boolean Union(int x , int y) {
		x = find(x);
		y = find(y);
		
		if(x == y)
			return true;
		return false;
	}
	
}