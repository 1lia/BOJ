import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        String s1 = br.readLine();
        String s2 = br.readLine();
        int N = s1.length();
        int M = s2.length();
        int[][] dp = new int[M][N];
        int res = 1000000000;
        Arrays.fill(dp[0] , 1000000000);
        for (int j = 0; j < N; j++) {
            if(s1.charAt(j) == s2.charAt(0)){
                dp[0][j] = 0;
            }
        }

        for (int i = 1; i < M; i++) {
            Arrays.fill(dp[i] , 1000000000);
            for (int j = 0; j < N; j++) {
                if(s1.charAt(j) == s2.charAt(i)){
                    if(j - 1 >= 0 && s1.charAt(j - 1) == s2.charAt(i - 1)){
                        for (int k = 0; k < N; k++) {
                            dp[i][j] = Math.min(dp[i][j] , dp[i - 1][k] + 1 + Math.abs(j - 1 - k));
                        }
                    }

                    if(j + 1 < N && s1.charAt(j + 1) == s2.charAt(i - 1)){
                        for (int k = 0; k < N; k++) {
                            dp[i][j] = Math.min(dp[i][j] , dp[i - 1][k] + 1 + Math.abs(j + 1 - k));
                        }
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            res = Math.min(res , dp[M - 1][i]);
        }

        if(res == 1000000000)
            res = -1;
        System.out.println(res);
    }
}