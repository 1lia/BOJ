import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while(T-->0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            ArrayList<Node>[] g = new ArrayList[N];
            ArrayList<Integer>[] add = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                g[i] = new ArrayList<>();
                add[i] = new ArrayList<>();
            }
            double[] arr = new double[N];
            double[] temp = new double[N];
            for (int i = 0; i < N; i++) {
                arr[i] = Double.parseDouble(st.nextToken());
            }
            for (int i = 0; i < l; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                double p = Double.parseDouble(st.nextToken());
                g[s].add(new Node(e , p));
                add[s].add(e);
                add[e].add(s);
            }


            for (int k = 0; k < t; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < g[i].size(); j++) {
                        Node node = g[i].get(j);
                        double mi = arr[i] * node.p;
                        arr[i] -= mi;
                        temp[node.v] += mi;
                    }
                }

                for (int j = 0; j < N; j++) {
                    arr[j] += temp[j];
                    temp[j] = 0;
                }
            }
            double res = Double.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                double d = 0;
                for (int j = 0; j < add[i].size(); j++) {
                    d += arr[add[i].get(j)];
                }
                res = Math.min(res , arr[i] + d);
            }
            sb.append(res).append('\n');
        }
        System.out.println(sb);
    }

    static class Node{
        int v;
        double p;

        public Node(int v, double p) {
            this.v = v;
            this.p = p;
        }
    }
}