public class Main {
    public static void main(String[] args) throws Exception{
        int x1 = readInt(); int y1 = readInt(); int x2 = readInt(); int y2 = readInt(); int x3 = readInt(); int y3 = readInt();
        double t = Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) * 0.5;
        int N = readInt();
        int res = 0;
        for (int i = 0; i < N; i++) {
            int x = readInt();
            int y = readInt();
            int a = (x * y2 + x2 * y3 + x3 * y) - (x2 * y + x3 * y2 + x * y3);
            int b = (x1 * y + x * y3 + x3 * y1) - (x * y1 + x3 * y + x1 * y3);
            int c = (x1 * y2 + x2 * y + x * y1) - (x2 * y1 + x * y2 + x1 * y);
            if((a >= 0 && b >= 0 && c >= 0) || (a <= 0 && b <= 0 && c <= 0)){
                res++;
            }
        }
        System.out.println(t);
        System.out.println(res);
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