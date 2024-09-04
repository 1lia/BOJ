import java.util.*;

class Main {
    public static ArrayList<Integer>[] g;
    public static void main(String[] args) throws Exception {
        int N = readInt();
        int[] arr = new int[N];
        int[] dp = new int[N];
        int[] idx = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = readInt();
        }
        dp[0] = arr[0];
        int cnt = 0;
        for (int i = 1; i < N; i++) {
            if(dp[cnt] <= arr[i]){
                dp[++cnt] = arr[i];
                idx[i] = cnt;
            } else if(arr[i] < dp[0]){
                dp[0] = arr[i];
                idx[i] = 0;
            } else{
                int s = 0;
                int e = cnt;
                int m;
                while(s < e){
                    m = (s + e) >> 1;
                    if(dp[m] <= arr[i]){
                        s = m + 1;
                    } else{
                        e = m;
                    }
                }
                dp[s] = arr[i];
                idx[i] = s;
            }

        }
        ArrayList<Integer> res = new ArrayList();
        StringBuilder sb = new StringBuilder();
        if(cnt + 4 < N){
            sb.append("NO");
        } else{
            int count = cnt;
            sb.append("YES").append('\n');
            for (int i = N - 1; i >= 0 ; i--) {
                if(idx[i] == count){
                    count--;
                } else{
                    res.add(i);
                }
            }
            sb.append(res.size()).append('\n');
            for (int i = 0; i < res.size(); i++) {
                int v = res.get(i);
                if(v == N - 1){
                    sb.append(N).append(' ').append(1000000000).append('\n');
                    arr[N - 1] = 1000000000;
                } else{
                    sb.append(v + 1).append(' ').append(arr[v + 1]).append('\n');
                    arr[v] = arr[v + 1];
                }
            }
        }
        System.out.println(sb);
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