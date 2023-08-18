import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static ArrayList<Integer>[] g;
    public static int[] match;
    public static boolean[] visit;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] map = new char[N][N];
        int[][] row = new int[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        int r = 1;
        boolean check = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 'X'){
                    if(check) {
                        r++;
                        check = false;
                    }
                } else{
                    row[i][j] = r;
                    check = true;
                }
            }
            if(check) {
                r++;
                check = false;
            }
        }
        g = new ArrayList[r+1];
        visit = new boolean[r+1];
        for (int i = 0; i <= r; i++) {
            g[i] = new ArrayList();
        }
        int l = 1;
        check = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[j][i] == 'X'){
                    if(check) {
                        l++;
                        check = false;
                    }
                } else{
                    g[row[j][i]].add(l);
                    check = true;
                }
            }
            if(check) {
                l++;
                check = false;
            }
        }
        match = new int[l+1];
        int res = 0;
        for (int i = 1; i <= r; i++) {
            Arrays.fill(visit,false);
            if(dfs(i))
                res++;
        }
        System.out.println(res);
    }

    public static boolean dfs(int v){
        visit[v] = true;
        for (int i = 0; i < g[v].size(); i++) {
            int t = g[v].get(i);
            if(match[t] == 0 || (!visit[match[t]] && dfs(match[t]))){
                match[t] = v;
                return true;
            }
        }
        return false;
    }
}