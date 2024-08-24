class Main {
    public static int S , x , y , dx ,dy;
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        while(true){
            S = readInt();
            x = readInt();
            y = readInt();
            dx = readInt();
            dy = readInt();
            if(S == 0){
                break;
            }

            if(((x + S) % S == 0 && dx % S == 0) || ((y + S) % S == 0 && dy % S == 0)){
                sb.append("The flea cannot escape from black squares.").append('\n');
                continue;
            }
            int cnt = -1;
            while(true){
                cnt++;
                if (check(x,y)){
                    sb.append("After ").append(cnt).append(" jumps the flea lands at (").append(x).append(", ").append(y).append(").\n");
                    break;
                }
                x += dx;
                y += dy;

                if(cnt == 1000000){
                    sb.append("The flea cannot escape from black squares.").append('\n');
                    break;
                }
            }
        }
        System.out.println(sb);
    }

    public static boolean check(int x , int y){
        int t = 0;
        t += x / S;
        t += y / S;
        if(x % S == 0 || y % S == 0){
            return false;
        }

        if((t & 1) != 0)
            return true;
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