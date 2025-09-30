import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{
        int N = readInt();
        int M = readInt();
        int K = readInt();
        ArrayList<Integer>[] g = new ArrayList[N + 1];
        int[] build = new int[N + 1];
        int[] in = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            g[i] = new ArrayList();
        }

        for (int i = 0; i < M; i++) {
            int x = readInt();
            int y = readInt();
            in[y]++;
            g[x].add(y);
        }

        for (int i = 0; i < K; i++) {
            int t = readInt();
            int v = readInt();
            if(t == 2){
                build[v]--;
                if(build[v] < 0){
                    System.out.println("Lier!");
                    return;
                } else if(build[v] == 0){
                    for (int j = 0; j < g[v].size(); j++) {
                        in[g[v].get(j)]++;
                    }
                }
            } else{
                if(in[v] != 0){
                    System.out.println("Lier!");
                    return;
                }
                if(build[v] == 0){
                    for (int j = 0; j < g[v].size(); j++) {
                        in[g[v].get(j)]--;
                    }
                }
                build[v]++;
            }
        }
        System.out.println("King-God-Emperor");
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