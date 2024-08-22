import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];
        int[][] cost = new int[N][M];
        boolean[][] visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        for (int i = 0; i < N; i++) {
            Arrays.fill(cost[i] , 100000000);
        }

        Queue<Pos> q = new ArrayDeque<>();
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        int x = 0;
        int y = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == '+'){
                    q.offer(new Pos(i,j,0));
                    cost[i][j] = 0;
                } else if(map[i][j] == 'V'){
                    x = i;
                    y = j;
                    visit[x][y] = true;
                }
            }
        }

        while(!q.isEmpty()){
            Pos p = q.poll();
            for (int i = 0; i < 4; i++) {
                int dx = p.x + "1102".charAt(i) - '1';
                int dy = p.y + "2011".charAt(i) - '1';
                if(0 <= dx && dx < N && 0 <= dy && dy < M && p.cost + 1 < cost[dx][dy]){
                    cost[dx][dy] = p.cost + 1;
                    q.offer(new Pos(dx , dy , p.cost + 1));
                }
            }
        }


        pq.offer(new Pos(x,y,cost[x][y]));
        int res = 10000000;
        while(!pq.isEmpty()){
            Pos p = pq.poll();
            res = Math.min(res , p.cost);
            if(map[p.x][p.y] == 'J'){
                System.out.println(res);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int dx = p.x + "1102".charAt(i) - '1';
                int dy = p.y + "2011".charAt(i) - '1';
                if(0 <= dx && dx < N && 0 <= dy && dy < M && !visit[dx][dy]){
                    visit[dx][dy] = true;
                    pq.offer(new Pos(dx , dy , cost[dx][dy]));
                }
            }

        }
    }
}

class Pos implements Comparable<Pos> {
    int x;
    int y;
    int cost;

    public Pos(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    @Override
    public int compareTo(Pos o) {
        return o.cost - this.cost;
    }
}