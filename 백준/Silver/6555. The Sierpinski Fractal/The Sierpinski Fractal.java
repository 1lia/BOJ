class Main {
    public static char[][] map;
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        while(true){
            int N = readInt();
            if(N == 0)
                break;
            int h = (int) Math.pow(2 , N);
            map = new char[h + 1][h << 1 + 1];
            dfs(1,1,h >> 1);
            int r = h << 1;
            for (int i = h; i > 0; i--) {
                temp = new StringBuilder();
                for (int j = 1; j <= r; j++) {
                    if(map[i][j] != 0) {
                        sb.append(temp).append(map[i][j]);
                        temp = new StringBuilder();
                    }
                    else temp.append(' ');
                }
                sb.append('\n');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    public static void dfs(int x , int y , int rr){
        if(rr == 1){
            draw(x,y);
        } else{
            dfs(x,y , rr >> 1);
            dfs(x , y + rr + rr , rr >> 1);
            dfs(x + rr , y + rr , rr >> 1);
        }
    }

    public static void draw(int x , int y){
        map[x][y] = '/';
        map[x][y + 1] = '_';
        map[x][y + 2] = '_';
        map[x][y + 3] = '\\';
        map[x + 1][y + 1] = '/';
        map[x + 1][y + 2] = '\\';
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