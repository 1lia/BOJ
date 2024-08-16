import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        int T = readInt();
        StringBuilder sb = new StringBuilder();
        for (int z = 1; z <= T; z++) {
            int N = readInt();
            Pos[] arr = new Pos[N];

            for (int i = 0; i < N; i++) {
                readInt();
            }
            for (int i = 0; i < N; i++) {
                arr[i] = new Pos(i,readInt());
            }

            Arrays.sort(arr);
            sb.append("Case #").append(z).append(':');
            for (int i = 0; i < N; i++) {
                sb.append(' ').append(arr[i].idx);
            }
            sb.append('\n');
        }
        System.out.println(sb);
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

class Pos implements Comparable<Pos>{
    int idx;
    int val;

    public Pos(int idx, int val) {
        this.idx = idx;
        this.val = val;
    }

    @Override
    public int compareTo(Pos o) {
        if(this.val == o.val){
            return this.idx - o.idx;
        }
        return o.val - this.val;
    }
}