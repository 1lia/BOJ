import java.util.*;

class Main {
    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        int N = readInt();
        Pos[] arr = new Pos[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new Pos(i + 1 , readInt() , readInt());
        }
        Arrays.sort(arr);
        for (int i = 0; i < N; i++) {
            sb.append(arr[i].v).append('\n');
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
    int v;
    double power;

    public Pos(int v, int a , int b) {
        this.v = v;
        this.power = (b - 1) / (double) a;
    }

    @Override
    public int compareTo(Pos o) {
        if(o.power > this.power){
            return 1;
        }
        return -1;
    }
}