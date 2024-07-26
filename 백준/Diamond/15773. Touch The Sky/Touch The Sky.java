import java.util.*;

class Main {
    public static void main(String[] args) throws Exception{
        PriorityQueue<Node> q = new PriorityQueue<>();
        PriorityQueue<Long> use = new PriorityQueue<>(Collections.reverseOrder());
        long N = readLong();
        while(N-->0){
            q.offer(new Node(readLong() , readLong()));
        }
        int cnt = 0;
        long size = 0;

        while(!q.isEmpty()){
            Node node = q.poll();
            if(size <= node.l){
                cnt++;
                size += node.d;
                use.offer(node.d);
            } else {
                long t = use.poll();
                use.offer(Math.min(t , node.d));
                size += Math.min(t , node.d) - t;
            }
        }
        System.out.println(cnt);
    }

    public static long readLong() throws Exception {
        long val = 0;
        long c = System.in.read();
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

class Node implements Comparable<Node>{
    long l;
    long d;
    long e;

    public Node(long l, long d) {
        this.l = l;
        this.d = d;
        this.e = l + d;
    }

    @Override
    public int compareTo(Node o) {
        if(this.e - o.e < 0){
            return -1;
        }
        return 1;
    }
}