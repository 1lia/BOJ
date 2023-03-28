import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}	
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				if(arr[i][j] == 1)
					arr[i][j] = Math.min(arr[i-1][j-1], Math.min(arr[i][j-1], arr[i-1][j])) + 1;
			}
		}
		int result = 0;
		int temp = 0;
		for (int i = 0; i <	N; i++) {
			temp = Arrays.stream(arr[i]).max().getAsInt();
			if(temp > result) {
				result = temp;
			}
		}
		System.out.println(result * result);
	}
}