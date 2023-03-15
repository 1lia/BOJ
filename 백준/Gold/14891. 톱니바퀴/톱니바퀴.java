import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		boolean[][] arr = new boolean[5][8];
		
		for (int i = 1; i <= 4; i++) {
			String s = br.readLine();
			for (int j = 0; j < 8; j++) {
//				s극 true
				if(s.charAt(j) == '1')
					arr[i][j] = true;
				
			}
		}
//		6 2
		int[][] rl = new int[5][2];
		for (int i = 1; i <= 4; i++) {
			rl[i][0] = 6;
			rl[i][1] = 2;
		}
		
		
//		시계방향돌면 -1   반시계면 +1    %8
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
//			오른쪽
			for (int j = num + 1; j <= 4; j++) {
//				왼쪽톱니바퀴의 오른쪽과 내 왼쪽이 같으면 그전까지 돌림
				if(arr[j-1][rl[j-1][1]] == arr[j][rl[j][0]]) {
					for (int k = j-1; k > num; k--) {
//						홀수면 반대로돌리고 짝수면 같이돌린다
						if((k + num) % 2 == 1) {
							rl[k][0] = (rl[k][0] + v + 8) % 8;
							rl[k][1] = (rl[k][1] + v + 8) % 8;
						} else {
							rl[k][0] = (rl[k][0] - v + 8) % 8;
							rl[k][1] = (rl[k][1] - v + 8) % 8;
						}
						
					}
					break;
				}
				if(j == 4) {
					for (int k = 4; k > num; k--) {
						if((k + num) % 2 == 1) {
							rl[k][0] = (rl[k][0] + v + 8) % 8;
							rl[k][1] = (rl[k][1] + v + 8) % 8;
						} else {
							rl[k][0] = (rl[k][0] - v + 8) % 8;
							rl[k][1] = (rl[k][1] - v + 8) % 8;
						}
					}
				}
			}
			
//			왼쪽
			for (int j = num - 1; j >= 1; j--) {
				if(arr[j+1][rl[j+1][0]] == arr[j][rl[j][1]]) {
					for (int k = j+1; k < num; k++) {
						if((k + num) % 2 == 1) {
							rl[k][0] = (rl[k][0] + v + 8) % 8;
							rl[k][1] = (rl[k][1] + v + 8) % 8;
						} else {
							rl[k][0] = (rl[k][0] - v + 8) % 8;
							rl[k][1] = (rl[k][1] - v + 8) % 8;
						}
					}
					break;
				}
				if(j == 1) {
					for (int k = 1; k < num; k++) {
						if((k + num) % 2 == 1) {
							rl[k][0] = (rl[k][0] + v + 8) % 8;
							rl[k][1] = (rl[k][1] + v + 8) % 8;
						} else {
							rl[k][0] = (rl[k][0] - v + 8) % 8;
							rl[k][1] = (rl[k][1] - v + 8) % 8;
						}
					}
				}
			}
			
			rl[num][0] = (rl[num][0] - v + 8) % 8;
			rl[num][1] = (rl[num][1] - v + 8) % 8;
		}
		
		int result = 0;
		for (int i = 1; i <= 4; i++) {
			if(arr[i][(rl[i][0] + 2) % 8])
				result += Math.pow(2, i - 1);
		}
		
		System.out.println(result);
	}
}