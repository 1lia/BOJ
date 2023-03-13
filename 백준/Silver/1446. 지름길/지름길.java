import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int D;
    public static void main(String[] args) throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	D = Integer.parseInt(st.nextToken());
    	int[] dp = new int[D+1];
    	Load[] arr = new Load[N];
    	for (int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
			arr[i] = new Load(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
    	Arrays.sort(arr);
    	Arrays.fill(dp, 2020202);
    	int num = 1;
    	int cnt = 0;
    	dp[0] = 0;
    	while(cnt < N && arr[cnt].start == 0) {
    		if(cnt < N && arr[cnt].end <= D)
    			dp[arr[cnt].end] = Math.min(arr[cnt].cost , dp[arr[cnt].end]);
    		cnt++;
    	}
    	
    	while(num <= D && cnt < N) {
    		dp[num] = Math.min(dp[num-1] + 1, dp[num]);
    		while(cnt < N && arr[cnt].start == num) {
    			if(arr[cnt].end <= D)
    				dp[arr[cnt].end] = Math.min(dp[num] + arr[cnt].cost , dp[arr[cnt].end]);
    			cnt++;
    		}
    		num++;
    	}
    	while(num <= D){
    		dp[num] = Math.min(dp[num-1] + 1, dp[num]);
    		num++;
    	}
    	System.out.println(dp[D]);
    }
}
class Load implements Comparable<Load>{
	int start;
	int end;
	int cost;
	public Load(int start, int end, int cost) {
		this.start = start;
		this.end = end;
		this.cost = cost;
	}
	@Override
	public int compareTo(Load o) {
		if(this.start < o.start) {
			return -1;
		}
		return 1;
	}
	
}