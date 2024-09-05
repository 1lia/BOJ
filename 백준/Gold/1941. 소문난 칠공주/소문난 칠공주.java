import java.io.*;

class Main {
    public static long res;
    public static int[] map;
    public static int visit;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[25];
        for (int i = 0; i < 5; i++) {
            String s = br.readLine();
            for (int j = 0; j < 5; j++) {
                if(s.charAt(j) == 'S'){
                    map[i * 5 + j] = 1;
                }
            }
        }
        dfs(0,0 , -1);
        System.out.println(res);
    }

    public static void dfs(int cnt , int cost , int num){
        if(cnt == 7) {
            int c = 0;
            int p = 0;
            boolean[] check = new boolean[25];
            for (int i = 0; i < 25; i++) {
                if((cost & (1 << i)) != 0){
                    c += map[i];
                    check[i] = true;
                    p = i;
                }
            }
            visit = 1 << p;
            if(c >= 4 && dfs2(p , check) == 7){
                res++;
            }
            return;
        }
        for (int i = num + 1; i < 25; i++) {
            if((cost & (1 << i)) == 0){
                dfs(cnt + 1 , cost | (1 << i) , i);
            }
        }
    }

    public static int dfs2(int v , boolean[] check){
        int cnt = 1;
        if(isRange(v + 5) && check[v + 5] && (visit & (1 << (v + 5))) == 0){
            visit |= (1 << (v + 5));
            cnt += dfs2(v + 5 , check);
        }
        if(isRange(v - 5) && check[v - 5] && (visit & (1 << (v - 5))) == 0){
            visit |= (1 << (v - 5));
            cnt += dfs2(v - 5 , check);
        }
        if(v % 5 != 4 && isRange(v + 1) && check[v + 1] && (visit & (1 << (v + 1))) == 0){
            visit |= (1 << (v + 1));
            cnt += dfs2(v + 1 , check);
        }
        if(v % 5 != 0 && isRange(v - 1) && check[v - 1] && (visit & (1 << (v - 1))) == 0){
            visit |= (1 << (v - 1));
            cnt += dfs2(v - 1 , check);
        }
        return cnt;
    }

    public static boolean isRange(int v){
        if(0 <= v && v < 25){
            return true;
        }
        return false;
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