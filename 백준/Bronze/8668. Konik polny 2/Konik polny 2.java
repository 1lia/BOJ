class Main {
    public static void main(String[] args) throws Exception {
        double x = readInt();
        double s = readInt();
        double sum = 0;
        int cnt = 0;
        while(sum <= x && cnt < 10000){
            sum += s;
            s /= 2;
            cnt++;
            if(cnt == 10000){
                System.out.println("NIE");
                return;
            }
        }
        System.out.println("TAK");
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