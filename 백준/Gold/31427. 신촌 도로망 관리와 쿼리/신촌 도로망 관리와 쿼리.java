import java.util.*;

public class Main {
	public static int N , M , Q;
	public static ArrayList<Node>[] load; 
	public static long[][][][][][] costCount;
	public static void main(String[] args) throws Exception {	
		StringBuilder sb = new StringBuilder();
		N = readInt(); M = readInt(); Q = readInt();
		costCount = new long[5][5][5][5][5][5];
		Pos[] arr = new Pos[5];
		load = new ArrayList[5];
		for (int i = 0; i < 5; i++) {
			load[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			int v = readInt();
			int u = readInt();
			int idx = readInt() - 17;
			load[idx].add(new Node(v , u));
		}
		int[] uf = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			uf[i] = i;
		}
		run(0 , 0 , new int[5] , uf , new long[5]);
		for (int i = 0; i < Q; i++) {
			for (int j = 0; j < 5; j++) {
				arr[j] = new Pos(j , readInt());
			}
			Arrays.sort(arr);
			long res = 0;
			for (int j = 0; j < 5; j++) {
				res += costCount[arr[0].idx][arr[1].idx][arr[2].idx][arr[3].idx][arr[4].idx][j] * arr[j].cost;
			}
			sb.append(res).append('\n');
		}
		System.out.println(sb);
	}
	
	public static void run(int cnt , int visit , int[] order , int[] uf , long[] count) {
		for (int i = 0; i < 5; i++) {
			if(((1 << i) & visit) == 0) {
				int[] next = new int[N + 1];
				for (int j = 1; j <= N; j++) {
					next[j] = uf[j];
				}
				long t = 0;
				
				for (int j = 0; j < load[i].size(); j++) {
					Node node = load[i].get(j);
					if(!union(next , node.v , node.u)) {
						merge(next , node.v , node.u);
						t++;
					}
				}
				count[cnt] = t;
				order[cnt] = i;	
				if(cnt == 4) {
					for (int j = 0; j < 5; j++) {
						costCount[order[0]][order[1]][order[2]][order[3]][order[4]][j] = count[j];
					}
				} else {
					run(cnt + 1 , visit | (1 << i) , order , next , count);
				}
			}
		}
	}
	
	public static boolean union(int[] arr , int a , int b) {
		a = find(arr , a);
		b = find(arr , b);
		if(a == b) {
			return true;
		}
		return false;
	}
	
	public static int find(int[] arr , int a) {
		if(a == arr[a])
			return a;
		return arr[a] = find(arr , arr[a]);
	}
	
	public static void merge(int[] arr , int a , int b) {
		a = find(arr , a);
		b = find(arr , b);
		arr[a] = b;
	}
	
	public static int readInt() throws Exception {
		int val = 0;
		int c = System.in.read();
		while (c <= ' ') {
			c = System.in.read();
		}
		boolean flag = (c == '-');
		if (flag)
			c = System.in.read();
		do {
			val = 10 * val + c - 48;
		} while ((c = System.in.read()) >= 48 && c <= 57);

		if (flag)
			return -val;
		return val;
	}
}

class Node {
	int v;
	int u;
	public Node(int v, int u) {
		this.v = v;
		this.u = u;
	}	
}


class Pos implements Comparable<Pos>{
	int idx;
	long cost;
	public Pos(int idx, long cost) {
		this.idx = idx;
		this.cost = cost;
	}
	@Override
	public int compareTo(Pos o) {
		return o.cost > this.cost ? -1 : 1;
	}
}