public class Main {
    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        long Q = readLong();
        while(Q-->0){
            long N = readLong();
            long N2 = N;
            int cnt = 0;
            while(N > 1){
                N>>=1;
                cnt++;
            }
            sb.append(cnt).append(' ');
            cnt = 0;
            while(N2 > 2){
                N2 /= 3;
                cnt += 2;
            }
            if(N2 == 2)
                cnt++;
            sb.append(cnt).append('\n');
        }
        System.out.println(sb);
    }
    public static long readLong() throws Exception {
        long val = 0;
        long c = System.in.read();
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