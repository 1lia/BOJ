import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer>[] g = new ArrayList[65];
        for (int i = 0; i <= 64; i++) {
            g[i] = new ArrayList<>();
        }

        for (int i = 1; i <= 56; i++) {
            int t = i % 8;
            if(t != 0 && i + 17 <= 64){
                g[i].add(i + 17);
                g[i + 17].add(i);
            }
            if(t != 0 && t != 7){
                g[i].add(i + 10);
                g[i + 10].add(i);
            }
            if(t != 1 && i + 15 <= 64){
                g[i].add(i + 15);
                g[i + 15].add(i);
            }
            if(t != 1 && t != 2){
                g[i].add(i + 6);
                g[i + 6].add(i);
            }
        }
        StringBuilder sb = new StringBuilder();

        while(true){
            int P = readInt();
            if(P == 0) {
                System.out.println(sb);
                return;
            }
            int[] arr = new int[P];
            for (int i = 0; i < P; i++) {
                arr[i] = readInt();
            }
            Queue<Node> q = new ArrayDeque<>();
            q.offer(new Node(readInt(),0,0));
            int move = 0;
            int out = 0;
            int res = -1;
            while(!q.isEmpty()){
                Node node = q.poll();
                if((node.bit & out) != out){
                    continue;
                }
                if(node.bit == (1 << P) - 1){
                    res = node.move;
                    break;
                }
                if(move != node.move){
                    for (int i = 0; i < P; i++) {
                        arr[i] += 8;
                        if(arr[i] > 64){
                            out |= (1 << i);
                        }
                    }
                    move++;
                }

                for (int i = 0; i < g[node.v].size(); i++) {
                    int nv = g[node.v].get(i);
                    for (int j = 0; j <= P; j++) {
                        if(j == P){
                            q.offer(new Node(nv,node.bit,node.move + 1));
                            break;
                        }
                        if(arr[j] == nv){
                            q.offer(new Node(nv,node.bit | (1 << j),node.move + 1));
                        }
                    }
                }
            }
            if(res == -1){
                sb.append("impossible").append('\n');
            } else{
                sb.append(res).append('\n');
            }
        }
    }

    static class Node{
        int v;
        int bit;
        int move;

        public Node(int v, int bit, int move) {
            this.v = v;
            this.bit = bit;
            this.move = move;
        }
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