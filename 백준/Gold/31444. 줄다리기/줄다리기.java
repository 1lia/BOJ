import java.util.Arrays;

public class Main {
	public static int[] arr;
	public static void main(String[] args) throws Exception {	
		int N = readInt();
		Team[] team = new Team[(N * N - N) >> 1];
		arr = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			arr[i] = i;
		}
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				int cost = readInt();
				if(i < j) {
					team[cnt++] = new Team(i, j, cost);
				}
			}
		}
		Arrays.sort(team);
		for (int i = 0; i < team.length; i++) {
			if(union(team[i].i , team[i].j)) {
				System.out.println(team[i].cost);
				return;
			}
			merge(team[i].i , team[i].j);
		}
	}
	
	public static int find(int x) {
		if(x == arr[x])
			return x;
		return arr[x] = find(arr[x]);
	}
	
	public static void merge(int x , int y) {
		x = find(x);
		y = find(y);
		if(x == y)
			return;
		
		arr[x] = y;
	}
	public static boolean union(int x , int y) {
		x = find(x);
		y = find(y);
		if(x == y)
			return true;
		return false;
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

class Team implements Comparable<Team>{
	int i;
	int j;
	int cost;
	public Team(int i, int j, int cost) {
		this.i = i;
		this.j = j;
		this.cost = cost;
	}
	@Override
	public int compareTo(Team o) {
		return this.cost - o.cost;
	}
}