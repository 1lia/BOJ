import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String s = br.readLine();
        String[] arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }
        Aho_Corasick aho = new Aho_Corasick(arr);
        int[] dp = aho.query(s);
        if(dp[s.length() - 1] == 0){
            System.out.println(-1);
            return;
        }
        int cnt = 1;
        int state = s.length() - 1 - dp[s.length() - 1];
        int next = state;
        for (int i = s.length() - 2; i >= 0; i--) {
            if(dp[i] != 0) {
                next = Math.min(next, i - dp[i]);
            }
            if(i <= state){
                if(next < i){
                    cnt++;
                    state = next;
                } else{
                    System.out.println(-1);
                    return;
                }
            }
        }
        System.out.println(cnt);
    }
}

class Aho_Corasick {
    private int N = 300005;
    private int C = 26;
    private int[][] trie = new int[N][C];
    private int[] fail = new int[N];
    private int[] term = new int[N];
    private int piv;

    Aho_Corasick(String[] s) {
        for (int i = 0; i < s.length; i++) {
            int p = 0;
            for (int j = 0; j < s[i].length(); j++) {
                if (trie[p][s[i].charAt(j) - 'a'] == 0)
                    trie[p][s[i].charAt(j) - 'a'] = ++piv;
                p = trie[p][s[i].charAt(j) - 'a'];
            }
            term[p] = s[i].length();
        }

        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < C; i++) {
            if (trie[0][i] != 0)
                q.add(trie[0][i]);
        }

        while (!q.isEmpty()) {
            int x = q.poll();
            for (int i = 0; i < C; i++) {
                if (trie[x][i] != 0) {
                    int p = fail[x];

                    while (p != 0 && trie[p][i] == 0)
                        p = fail[p];

                    p = trie[p][i];
                    fail[trie[x][i]] = p;

                    if (term[p] != 0)
                        term[trie[x][i]] = Math.max(term[p] , term[trie[x][i]]);

                    q.add(trie[x][i]);
                }
            }
        }
    }

    public int[] query(String s) {
        int[] arr = new int[s.length()];
        int p = 0;

        for (int i = 0; i < s.length(); i++) {
            while (p != 0 && trie[p][s.charAt(i) - 'a'] == 0) {
                p = fail[p];
            }

            p = trie[p][s.charAt(i) - 'a'];

            if (term[p] != 0) {
                arr[i] = term[p];
            }
        }
        return arr;
    }
}