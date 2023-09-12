import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws Exception {
        int M = readInt();
        int N = readInt();
        int[][] arr = new int[N][M];
        boolean[][][] visit = new boolean[N][M][2];
        int x = 0;
        int y = 0;
        Queue<Pos> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = readInt();
                if(arr[i][j] == 2){
                    x = i;
                    y = j;
                }
            }
        }
        q.offer(new Pos(x,y,0,0));
        visit[x][y][0] = true;
        int[][] dxy = {{0,0,-1,1},{1,-1,0,0}};
        while(!q.isEmpty()){
            Pos p = q.poll();
            for (int i = 0; i < 4; i++) {
                int dx = p.x + dxy[0][i];
                int dy = p.y + dxy[1][i];

                if(0 <= dx && dx < N && 0 <= dy && dy < M && arr[dx][dy] != 1) {
                    if(arr[dx][dy] == 0 && !visit[dx][dy][p.t]){
                        visit[dx][dy][p.t] = true;
                        q.offer(new Pos(dx,dy,p.t , p.cnt + 1));
                    }
                    if((arr[dx][dy] == 4) && !visit[dx][dy][1]){
                        visit[dx][dy][1] = true;
                        q.offer(new Pos(dx,dy,1 , p.cnt + 1));
                    }
                    if(arr[dx][dy] == 3 && p.t == 1){
                        System.out.println(p.cnt + 1);
                        return;
                    }
                    if(arr[dx][dy] == 2 && !visit[dx][dy][1] && p.t == 1){
                        visit[dx][dy][1] = true;
                        q.offer(new Pos(dx,dy,1 , p.cnt + 1));
                    }
                }
            }
        }
    }
    public static int readInt() throws Exception {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        boolean flag = (c == '-');
        if (flag) c = System.in.read();
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);

        if (flag) return -val;
        return val;
    }
}

class Pos{
    int x;
    int y;
    int t;
    int cnt;

    public Pos(int x, int y, int t, int cnt) {
        this.x = x;
        this.y = y;
        this.t = t;
        this.cnt = cnt;
    }
}