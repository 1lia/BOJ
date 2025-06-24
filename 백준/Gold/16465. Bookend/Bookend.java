public class Main {
    public static void main(String[] args) throws Exception {
        int N = readInt();
        int M = readInt();
        int L = readInt();
        int len = 0;
        for (int i = 0; i < N; i++) {
            len += readInt();
        }
        int res = 0;
        if(len == M)
            res = 0;
        else if(len > M)
            res = -1;
        else if(L >= M)
            res = -1;
        else if(L <= len)
            res = 1;
        else if(M - len >= L)
            res = 1;
        else
            res = -1;
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