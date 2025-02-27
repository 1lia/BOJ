import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Queue<Node> q = new ArrayDeque();
        int N = readInt();
        int K = readInt();
        if(N == K) {
            System.out.println(0);
            return;
        }
        boolean[] odd = new boolean[500001];
        boolean[] even = new boolean[500001];

        int[] time = new int[500001];
        int t = 1;
        int cnt = 1;
        int res = 1000000;
        even[N] = true;
        q.offer(new Node(N , 0));

        while(K + t <= 500000){
            K += t;
            time[K] = cnt;
            cnt++;
            t++;
        }

        while(!q.isEmpty()){
            Node node = q.poll();
            if(time[node.v] != 0 && node.time <= time[node.v] && ((node.time ^ time[node.v]) & 1) == 0){
                res = Math.min(res , time[node.v]);
            }
            if((node.time & 1) == 0){
                if(0 <= node.v - 1 && !odd[node.v - 1]){
                    odd[node.v - 1] = true;
                    q.offer(new Node(node.v - 1 , node.time + 1));
                }
                if(node.v + 1 <= 500000 && !odd[node.v + 1]){
                    odd[node.v + 1] = true;
                    q.offer(new Node(node.v + 1 , node.time + 1));
                }
                if((node.v << 1) <= 500000 && !odd[node.v << 1]){
                    odd[node.v << 1] = true;
                    q.offer(new Node(node.v << 1 , node.time + 1));
                }
            } else {
                if(0 <= node.v - 1 && !even[node.v - 1]){
                    even[node.v - 1] = true;
                    q.offer(new Node(node.v - 1 , node.time + 1));
                }
                if(node.v + 1 <= 500000 && !even[node.v + 1]){
                    even[node.v + 1] = true;
                    q.offer(new Node(node.v + 1 , node.time + 1));
                }
                if((node.v << 1) <= 500000 && !even[node.v << 1]){
                    even[node.v << 1] = true;
                    q.offer(new Node(node.v << 1 , node.time + 1));
                }
            }
        }

        if(res == 1000000){
            System.out.println(-1);
            return;
        }
        System.out.println(res);
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

class Node{
    int v;
    int time;

    public Node(int v, int time) {
        this.v = v;
        this.time = time;
    }
}