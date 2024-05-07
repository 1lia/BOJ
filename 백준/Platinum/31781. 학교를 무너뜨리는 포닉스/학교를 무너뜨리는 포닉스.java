import java.util.*;

public class Main {
	public static int[] dsu;
	public static List<Block>[] school; 
	
	public static void main(String[] args) throws Exception {
		int res = 1;
		int N = readInt();
		int H = readInt();
		dsu = new int[N + 1];
		school = new ArrayList[H + 1];
		for (int i = 0; i <= H; i++) {
			school[i] = new ArrayList<Block>();
		}
		for (int i = 1; i <= N; i++) {
			school[readInt()].add(new Block(i , readInt() , readInt() , 1));
			dsu[i] = -1;
		}
		
		for (int i = 1; i <= H; i++) {
			Collections.sort(school[i]);
		}
		
		for (int i = 2; i <= H; i++) {
			for (int j = 0; j < school[i].size(); j++) {
				Block block = school[i].get(j);
				int s = findStart(i - 1 , block.s , block.e);
				boolean flag = true;
				for (int k = s + 1; k < school[i - 1].size(); k++) {
					Block b = school[i - 1].get(k); 
					if(b.s < block.e) {
						if(!union(school[i-1].get(s).idx , b.idx)){
							flag = false;
							break;
						}
					} else {
						break;
					}
				}
				
				if(flag) {
					merge(school[i-1].get(s).idx , block.idx);
				}
				
			}
		}
		for (int i = 1; i <= H; i++) {
			for (int j = 0; j < school[i].size(); j++) {
				Block block = school[i].get(j);
				res = Math.max(res, size(block.idx));
			}
		}
		
		System.out.println(res);
	}
	
	public static int findStart(int h , int start , int end){
		int s = 0;
		int e = school[h].size() - 1;
		
		
		while(s != e) {
			int m = (s + e) >> 1;
			Block block = school[h].get(m);
			if(end <= block.s) {
				e = m - 1;
			} else if(start >= block.e){
				s = m + 1;
			} else {
				e = m;
			}
		}	
		return s;
	}


	public static int find(int n) {
		if(dsu[n] < 0)
			return n;
		return dsu[n] = find(dsu[n]);
	}
	
	public static void merge(int a , int b) {
		a = find(a);
		b = find(b);
		if (a == b) 
			return;
		
	    if (size(a) > size(b)) {
	    	a ^= b; b ^= a; a ^= b;
	    }
	    
	    dsu[b] += dsu[a];
	    dsu[a] = b;
	}
	public static boolean union(int a , int b) {
		if(find(a) == find(b)){
			return true;
		}
		return false;
	}

	public static int size(int n) { 
		return -dsu[find(n)]; 
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

class Block implements Comparable<Block>{
	int idx;
	int s;
	int e;
	int cnt;
	public Block(int idx, int s, int e, int cnt) {
		this.idx = idx;
		this.s = s;
		this.e = e;
		this.cnt = cnt;
	}
	@Override
	public int compareTo(Block o) {
		return this.s - o.s;
	}
}