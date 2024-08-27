import java.io.*;
import java.util.*;

class Main {
    public static char[][] map;
    public static int N;
    public static ArrayList<Pos> res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N + 1][N + 1];
        res = new ArrayList<>();
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            for (int j = 1; j <= N; j++) {
                map[i][j] = str.charAt(j - 1);
                if(map[i][j] == 'o')
                    cnt++;

            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(map[i][j] == 'K' && dfs(i,j , cnt)){
                    for (int k = res.size() - 1; k >= 0; k--) {
                        Pos p = res.get(k);
                        sb.append(p.x).append(' ').append(p.y).append('\n');
                    }
                    System.out.println(sb);
                    return;
                }
            }
        }
        System.out.println("impossible");
    }

    private static boolean dfs(int x, int y , int cnt) {
        if(cnt == 0){
            res.add(new Pos(x , y));
            return true;
        }

        for (int i = 0; i < 4; i++) {
            int px = "0220".charAt(i) - '1';
            int py = "0202".charAt(i) - '1';
            int dx = x + px + px;
            int dy = y + py + py;
            if(isRange(dx , dy) && map[dx][dy] == '+' && map[dx - px][dy - py] == 'o'){
                map[x][y] = '+';
                map[dx][dy] = 'K';
                map[dx - px][dy - py] = '+';
                if (dfs(dx , dy , cnt - 1)){
                    res.add(new Pos(x , y));
                    return true;
                }
                map[x][y] = 'K';
                map[dx][dy] = '+';
                map[dx - px][dy - py] = 'o';
            }
        }
        return false;
    }

    private static boolean isRange(int x , int y){
        if(0 < x && x <= N && 0 < y && y <= N)
            return true;
        return false;
    }

    static class Pos{
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}