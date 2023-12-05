import java.io.*;
import java.util.*;

public class Main {
	public static int N, M;
	public static int[] matchA;
	public static int[] matchB;
	public static int[] visitA;
	public static int[] visitB;
	public static char[] resA;
	public static char[] resB;
	public static ArrayList<Integer>[] gA;
	public static ArrayList<Integer>[] gB;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int[][] dxy = { { 1, -1, 0, 0 }, { 0, 0, 1, -1 } };

		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int size = N * M;
			if (size == 0) {
				break;
			}
			int[][] map = new int[N][M];
			int cntA = 0;
			int cntB = 0;
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < M; j++) {
					if (s.charAt(j) == '.') {
						if (((i + j) & 1) == 0) {
							map[i][j] = ++cntA;
						} else {
							map[i][j] = ++cntB;
						}
					}
				}
			}
			init(cntA , cntB);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (((i + j) & 1) == 0 && map[i][j] != 0) {
						for (int k = 0; k < 4; k++) {
							int dx = i + dxy[0][k];
							int dy = j + dxy[1][k];
							if(0 <= dx && dx < N && 0 <= dy && dy < M && map[dx][dy] != 0) {
								gA[map[i][j]].add(map[dx][dy]);
								gB[map[dx][dy]].add(map[i][j]);
							}
						}
					} 		
				}
			}

			for (int i = 1; i <= cntA; i++) {
				dfs(i,i);
			}
			for (int i = 1; i <= cntB; i++) {
				if(matchB[i] != 0) {
					matchA[matchB[i]] = i;
				}
			}
			
			Arrays.fill(visitA, 0);
			int count = 1;
			for (int i = 1; i <= cntA; i++) {
				if(resA[i] == 0) {
					solveA(i , 0 , count++);
				}
			}

			for (int i = 1; i <= cntB; i++) {
				if(resB[i] == 0) {
					solveB(i , 1 , count++);
				}
			}
			
			for (int i = 1; i <= cntA; i++) {
				if(resA[i] == 0) {
					resA[i] = 'A';
				}
			}
			
			for (int i = 1; i <= cntB; i++) {
				if(resB[i] == 0) {
					resB[i] = 'A';
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] == 0) {
						sb.append('X');
					} else {
						if (((i + j) & 1) == 0) {
							sb.append(resA[map[i][j]]);
						} else {
							sb.append(resB[map[i][j]]);
						}
					}
				}
				sb.append('\n');
			}
			sb.append('\n');
		}
		sb.deleteCharAt(sb.length()-1);
		sb.deleteCharAt(sb.length()-1);
		System.out.println(sb);
	}


	private static boolean solveA(int v, int check , int t) {
		boolean flag = false;
		if(check == 0) {
			if(matchA[v] == 0) {
				resA[v] = 'B';
				return true;
			}
			visitA[v] = t;
			flag = solveA(matchA[v] , 1 , t);
			if(flag) {
				resA[v] = 'B';
			}
		} else {
			visitB[v] = t;
			for (int i = 0; i < gB[v].size(); i++) {
				int nextA = gB[v].get(i);
				if(visitA[nextA] != t && !flag) {
					flag |= solveA(nextA , 0 , t);
				}
			}
		}
		return flag;
	}
	
	private static boolean solveB(int v, int check , int t) {
		boolean flag = false;
		if(check == 1) {
			if(matchB[v] == 0) {
				resB[v] = 'B';
				return true;
			}
			visitB[v] = t;
			flag = solveB(matchB[v] , 0 , t);
			if(flag) {
				resB[v] = 'B';
			}
		} else {
			visitA[v] = t;
			for (int i = 0; i < gA[v].size(); i++) {
				int nextB = gA[v].get(i);
				if(visitB[nextB] != t && !flag) {
					flag |= solveB(nextB , 1 , t);
				}
			}
		}
		return flag;
	}

	private static boolean dfs(int v , int cnt) {
		visitA[v] = cnt;
		for (int i = 0; i < gA[v].size(); i++) {
			int next = gA[v].get(i);
			if(matchB[next] == 0 || (visitA[matchB[next]] != cnt && dfs(matchB[next] , cnt))) {
				matchB[next] = v;
				return true;
			}
		}
		return false;
	}
	
	private static void init(int cntA , int cntB) {
		matchA = new int[cntA + 1];
		matchB = new int[cntB + 1];
		visitA = new int[cntA + 1];
		visitB = new int[cntB + 1];
		resA = new char[cntA + 1];
		resB = new char[cntB + 1];
		gA = new ArrayList[cntA + 1];
		gB = new ArrayList[cntB + 1];
		for (int i = 0; i <= cntA; i++) {
			gA[i] = new ArrayList<>();
		}
		for (int i = 0; i <= cntB; i++) {
			gB[i] = new ArrayList<>();
		}
	}
}