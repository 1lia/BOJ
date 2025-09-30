public class Main {
    public static double[][] g;
    public static double[] len;
    public static double[][] lenSum;
    public static double min,max;

    public static void main(String[] args) throws Exception{
        int T = readInt();
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            int N = readInt();
            len = new double[N + 1];
            g = new double[N + 1][2];
            lenSum = new double[N + 1][2];
            min = 0;
            max = 0;
            for (int i = 1; i <= N; i++) {
                len[i] = readInt();
                g[i][0] = readInt();
                g[i][1] = readInt();
            }
            dfs(1);
            dfs2(1 , 0);
            sb.append(max - min).append('\n');

        }
        System.out.println(sb);
    }

    public static void dfs2(int v , double t){
        double lv = lenSum[v][0];
        double rv = lenSum[v][1];
        double l = t - (len[v] * rv / (lv + rv));
        double r = t + (len[v] * lv / (lv + rv));
        min = Math.min(min , l);
        max = Math.max(max , r);
        if(g[v][0] < 0){
            dfs2((int) -g[v][0] , l);
        }
        if(g[v][1] < 0){
            dfs2((int) -g[v][1] , r);
        }
    }

    public static double dfs(int v){ //왼줄 , 오른줄 , 무게
        double l = g[v][0];
        double r = g[v][1];
        if(l < 0){
            l = dfs((int) -l);
        }
        if(r < 0){
            r = dfs((int) -r);
        }
        lenSum[v][0] = l;
        lenSum[v][1] = r;
        return l + r;
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