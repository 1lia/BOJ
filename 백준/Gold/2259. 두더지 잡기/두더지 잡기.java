import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        int N = readInt();
        int S = readInt();
        Node[] arr = new Node[N];
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new Node(readInt(),readInt(),readInt());
        }
        Arrays.sort(arr);
        int res = 0;
        for (int i = 0; i < N; i++) {
            if(dist(arr[i].x ,arr[i].y, 0 ,0) <= arr[i].t * S){
                dp[i] = 1;
            }
            for (int j = i - 1; j >= 0 ; j--) {
                if(dp[j] > 0 && (dist(arr[i].x , arr[i].y , arr[j].x , arr[j].y) <= (arr[i].t - arr[j].t) * S)){
                    dp[i] = Math.max(dp[i] , dp[j] + 1);
                }
            }
            res = Math.max(res , dp[i]);
        }
        System.out.println(res);
    }

    public static double dist(int x1 , int y1 , int x2 , int y2){
        return Math.sqrt(Math.pow(x1 - x2 , 2) + Math.pow(y1 - y2 , 2));
    }

    static class Node implements Comparable<Node>{
        int x;
        int y;
        int t;

        public Node(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }

        @Override
        public int compareTo(Node o) {
            return this.t - o.t;
        }
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