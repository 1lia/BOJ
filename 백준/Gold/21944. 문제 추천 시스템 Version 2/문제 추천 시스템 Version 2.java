import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		TreeSet<Problem> problem = new TreeSet<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			int G = Integer.parseInt(st.nextToken());
			problem.add(new Problem(P , L , G));
			map.put(P, L);
		}
		
		int M = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			if(s.equals("add")) {
				int P = Integer.parseInt(st.nextToken());
				int L = Integer.parseInt(st.nextToken());
				int G = Integer.parseInt(st.nextToken());
				problem.add(new Problem(P , L , G));
				map.put(P, L);
			} else if(s.equals("recommend")) {
				int G = Integer.parseInt(st.nextToken());
				
				if(Integer.parseInt(st.nextToken()) == 1){
					Problem p = problem.first();
					while(true) {
						if(p.type == G)
							break;
						p = problem.higher(p);
					}
					sb.append(p.idx).append('\n');
				} else {
					Problem p = problem.last();
					while(true) {
						if(p.type == G)
							break;
						p = problem.lower(p);
					}
					sb.append(p.idx).append('\n');
				}
				
			} else if(s.equals("recommend2")) {
				if(Integer.parseInt(st.nextToken()) == 1){
					sb.append(problem.first().idx).append('\n');
				} else {
					sb.append(problem.last().idx).append('\n');
				}
				
			} else if(s.equals("recommend3")) {
				int x = Integer.parseInt(st.nextToken());
				int L = Integer.parseInt(st.nextToken());
				Problem p = null;
				if(x == 1){
					p = problem.lower(new Problem(Integer.MAX_VALUE, L-1));
				} else {
					p = problem.higher(new Problem(0, L));
				}
				if(p == null)
					sb.append(-1).append('\n');
				else
					sb.append(p.idx).append('\n');	
			} else {
				int P = Integer.parseInt(st.nextToken());
				problem.remove(new Problem(P, map.get(P)));
				map.remove(P);
			}
		}
		System.out.println(sb);
	}
}

class Problem implements Comparable<Problem>{
	int idx;
	int rank;
	int type;
	
	public Problem(int idx, int rank) {
		this.idx = idx;
		this.rank = rank;
	}

	public Problem(int idx, int rank , int type) {
		this.idx = idx;
		this.rank = rank;
		this.type = type;
	}
	@Override
	public int compareTo(Problem o) {
		if(this.rank != o.rank)
			return o.rank - this.rank;
		return o.idx - this.idx;
	}
}