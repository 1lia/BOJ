import java.io.*;
import java.util.*;

class Main {
    public static char[][] map;
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        StringBuilder sb = new StringBuilder();
        int t = N / 3;
        int M = t * 5 + t;
        int cnt = 0;
        while(t > 0){
            t >>= 1;
            cnt++;
        }

        map = new char[N + 1][M + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(map[i] , ' ');
        }
        dfs(1,1, cnt);

        for (int i = N; i > 0; i--) {
            for (int j = 1; j < M; j++) {
                sb.append(map[i][j]);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    public static void dfs(int x , int y , int cnt){
        if(cnt == 1){
            draw(x,y);
        } else{
            dfs(x,y,cnt - 1);
            dfs(x , y + (int) Math.pow(2,cnt - 1) * 3 , cnt - 1);
            dfs(x + (int) Math.pow(2,cnt - 2) * 3, y + (int) Math.pow(2,cnt - 2) * 3 ,cnt - 1);
        }
    }

    public static void draw(int x , int y){
        map[x][y] = '*';
        map[x][y + 1] = '*';
        map[x][y + 2] = '*';
        map[x][y + 3] = '*';
        map[x][y + 4] = '*';
        map[x + 1][y + 1] = '*';
        map[x + 1][y + 3] = '*';
        map[x + 2][y + 2] = '*';
    }
}