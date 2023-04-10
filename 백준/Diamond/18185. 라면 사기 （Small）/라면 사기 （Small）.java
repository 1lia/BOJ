import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long result = 0;
		long maxsize = 0;
		long temp = 0;

		long[] arr = new long[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}

		for (int i = 1; i + 1 < N; i++) {
//				2개짜리
			if (arr[i] > arr[i + 1]) {
				maxsize = arr[i] - arr[i + 1];
				temp = Math.min(maxsize, Math.min(arr[i - 1], arr[i]));
				result += temp * (5);
				arr[i - 1] -= temp;
				arr[i] -= temp;
			}

			temp = Math.min(arr[i + 1], Math.min(arr[i - 1], arr[i]));
			result += temp * (7);
			arr[i - 1] -= temp;
			arr[i] -= temp;
			arr[i + 1] -= temp;

			result += arr[i - 1] * 3;
			arr[i - 1] = 0;
		}

		temp = Math.min(arr[N - 2], arr[N - 1]);
		result += temp * (5);
		arr[N - 2] -= temp;
		arr[N - 1] -= temp;

		temp = Math.max(arr[N - 2], arr[N - 1]);
		result += temp * 3;

		System.out.println(result);

	}
}