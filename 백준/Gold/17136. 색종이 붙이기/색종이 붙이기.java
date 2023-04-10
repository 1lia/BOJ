import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int result = 26; // 결과값
	public static int[][] arr = new int[10][10]; //
	public static int[] countnum = new int[6];

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int size = 0;
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < 10; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) {
					size++;
				}
			}
		}
		dfs(0, size, 0,0);
		if (result == 26)
			System.out.println(-1);
		else
			System.out.println(result);
	}

	public static void dfs(int cnt, int size, int x, int y) {
		if(checkcount())
			return;
		if(size == 0) {
			result = Math.min(result, cnt);
			return;
		}
		if(result - 1 <= cnt) {
			return;
		}
		while(x < 10 && y < 10) {
			if(arr[x][y] == 1) {	
				for (int i = 1; i <= Math.min(5, 10-Math.max(x, y)); i++) {
					if(check(x,y,i)) {
						sub(x,y,i);
						countnum[i]++;
						dfs(cnt + 1 , size - (i*i) , x , y);
						countnum[i]--;
						add(x,y,i);
					}	
				}
				break;
			}
			if(y == 9) {
				x++;
				y = 0;
			} else
				y++;
		}
	}

	// 지금 x , y 좌표로부터 n크기의 색종이를 덮을수 있는지 확인
	public static boolean check(int x, int y, int n) {
		for (int i = x; i < x + n; i++) {
			for (int j = y; j < y + n; j++) {
				if (arr[i][j] != 1)
					return false;
			}
		}
		return true;
	}

	public static void sub(int x, int y, int n) {
		for (int i = x; i < x + n; i++) {
			for (int j = y; j < y + n; j++) {
				arr[i][j] = 0;
			}
		}
	}

	public static void add(int x, int y, int n) {
		for (int i = x; i < x + n; i++) {
			for (int j = y; j < y + n; j++) {
				arr[i][j] = 1;
			}
		}
	}

	public static boolean checkcount() {
		for (int i = 1; i <= 5; i++) {
			if (countnum[i] > 5)
				return true;
		}
		return false;
	}
}