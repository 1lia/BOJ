import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws Exception {
        int N = readInt();
        int M = readInt();
        int[][] map = new int[N][M];
        boolean[][] visit = new boolean[N][M];
        Queue<Pos> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = readInt();
                if(i == 0 && map[i][j] == 1) {
                    q.offer(new Pos(i, j , 0));
                    visit[i][j] = true;
                }
            }
        }
        int Q = readInt();
        int[][] dxy = new int[Q][2];

        for (int i = 0; i < Q; i++) {
            dxy[i][0] = readInt();
            dxy[i][1] = readInt();
        }

        while(!q.isEmpty()){
            Pos p = q.poll();
            if(p.x == N - 1){
                System.out.println(p.cnt);
                return;
            }
            for (int i = 0; i < dxy.length; i++) {
                int dx = p.x + dxy[i][0];
                int dy = p.y + dxy[i][1];
                if(0 <= dx && dx < N && 0 <= dy && dy < M && map[dx][dy] == 1 && !visit[dx][dy]){
                    visit[dx][dy] = true;
                    q.offer(new Pos(dx , dy , p.cnt + 1));
                }
            }
        }
        System.out.println(-1);
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

class Pos{
    int x;
    int y;
    int cnt;

    public Pos(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}