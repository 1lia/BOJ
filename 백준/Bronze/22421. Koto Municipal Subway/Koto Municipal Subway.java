class Main {
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        while(true){
            int N = readInt();
            int cost = readInt();
            if(N == 0)
                break;
            double res = 1000000000;
            for (int i = 0; i < N; i++) {
                res = Math.min(res , Math.abs(Math.sqrt(Math.pow(i , 2) + Math.pow(N - i , 2)) - cost));
            }
            sb.append(res).append('\n');
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