import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String[] map = new String[N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine();
        }
        st = new StringTokenizer(br.readLine());
        int x1 = Integer.parseInt(st.nextToken()) - 1;
        int y1 = Integer.parseInt(st.nextToken()) - 1;
        int x2 = Integer.parseInt(st.nextToken()) - 1;
        int y2 = Integer.parseInt(st.nextToken()) - 1;
        int[][] visit = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(visit[i] , 100000000);
        }
        visit[x1][y1] = 0;
        Deque<Node> q = new ArrayDeque<>();
        q.offer(new Node(x1,y1,0 , 15));
        while(!q.isEmpty()){
            Node node = q.poll();
            for (int i = 0; i < 4; i++) {
                int dx = node.x;
                int dy = node.y;
                if((node.bit & (1 << i)) != 0){
                    boolean flag = false;
                    for (int j = 0; j < K; j++) {
                        dx += "1102".charAt(i) - '1';
                        dy += "0211".charAt(i) - '1';
                        if(0 <= dx && dx < N && 0 <= dy && dy < M && map[dx].charAt(dy) == '.'){
                            if(visit[dx][dy] > node.cnt + 1){
                                visit[dx][dy] = node.cnt + 1;
                                flag = true;
                                if(i < 2)
                                    q.offer(new Node(dx,dy,node.cnt + 1 , 12));
                                else q.offer(new Node(dx,dy,node.cnt + 1 , 3));
                            } else if(visit[dx][dy] == node.cnt)
                                break;
                        } else break;
                    }
                    if(flag)
                        q.peekLast().bit |= (1 << i);
                }
            }
        }
        if(visit[x2][y2] == 100000000)
            System.out.println(-1);
        else System.out.println(visit[x2][y2]);

    }
    static class Node{
        int x;
        int y;
        int cnt;
        int bit;

        public Node(int x, int y, int cnt, int bit) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.bit = bit;
        }
    }
}