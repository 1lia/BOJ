import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static int N;
	public static int[][] map;
	public static int result;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N  = Integer.parseInt(bf.readLine());
		map = new int[N][N];
		int[][][] check = new int[N][1 << N][N];   //cnt , 방문인원
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < (1 << N); j++) {
				for (int j2 = 0; j2 < N; j2++) {
					check[i][j][j2] = -1;
				}
			}
		}
		for (int i = 0; i < N; i++) {
			String s = bf.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = (int)(s.charAt(j) - '0');
			}
		}
		
		dfs(0 , 0 , 1 , 1 ,check);
		System.out.println(result);
		
	}

	private static void dfs(int num , int cost , int visit , int cnt , int[][][] check) {
		if(check[cnt-1][visit][num] <= cost && check[cnt-1][visit][num] != -1) {
			return;
		} else {
			check[cnt-1][visit][num] = cost;
		}
		if (result == N)
			return;
		if (result < cnt) {
			result = cnt;
		}
		
		for (int i = 0; i < N; i++) {
			if((visit | 1 << i) != visit && cost <= map[num][i] && i != num) {
				dfs(i , map[num][i] , visit | (1 << i) , cnt + 1 , check);
			}		
		}
	}
}