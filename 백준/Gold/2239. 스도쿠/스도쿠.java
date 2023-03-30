import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static int[][] map;
	public static int[][] dp;
	public static int[][] dp2;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[10][10];
		dp = new int[10][10];  //[ 숫자 ] [ 행 x ] = 열 y  
		dp2 = new int[10][2];   //[ 숫자 ] [check x , check position]
		
		for (int i = 1; i < 10; i++) {
			String s = br.readLine();
			for (int j = 1; j < 10; j++) {
				map[i][j] = s.charAt(j-1) - '0';
				if(map[i][j] != 0) {
					dp[map[i][j]][i] = j;
					dp2[map[i][j]][0] |= (1 << j);
					dp2[map[i][j]][1] |= (1 << decomposition(i,j));
				}
			}
		}
		dfs(1,1);
	}
	
	public static void dfs(int x , int y) {
		if(x == 10) {
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i < 10; i++) {
				for (int j = 1; j < 10; j++) {
					sb.append(map[i][j]);
				}
				sb.append('\n');
			}
			System.out.println(sb.toString());
			System.exit(0);
		}
		int position = 0;
		if(map[x][y] == 0) {
//			1부터 넣어본다
			for (int i = 1; i < 10; i++) {
//				x , y 좌표에 i숫자가 가능하면 넣는다
				position = decomposition(x,y);
				if(check(x,y,i,position)) {
					dp[i][x] = y;    //y를 넣고
					dp2[i][0] |= 1 << y; // y열을 넣는다
					dp2[i][1] |= 1 << position; //구간을 넣는다
					map[x][y] = i;
//					다음좌표
					if(y == 9) {
						dfs(x + 1 , 1);
					} else {
						dfs(x , y + 1);
					}
//				안되는건 다시0으로
					map[x][y] = 0;
					dp[i][x] = 0; 
					dp2[i][0] &= ~(1 << y);
					dp2[i][1] &= ~(1 << position); 
				}
			}
		} else {
			if(y == 9) {
				dfs(x + 1 , 1);
			} else {
				dfs(x , y + 1);
			}
		}
	}
	
	public static boolean check(int x, int y, int i , int position) {
		if(dp[i][x] == 0) {
			if((dp2[i][0] & 1 << y) != 0)
				return false;
			if((dp2[i][1] & 1 << position) != 0) {
				return false;
			}
			return true;
		}
		return false;
	}

	public static int decomposition(int x , int y) {
		return (x - 1) / 3 * 3 + ((y - 1) / 3) + 1;
	}
}