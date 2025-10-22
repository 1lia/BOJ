public class Main {
    public static void main(String[] args) throws Exception{
        int N = (int) readLong();
        long B = readLong();
        int s = 0;
        int t = 0;
        long[] visit = new long[70000];

        for (int i = 0; i < N; i++) {
            if(readLong() == 1)
                s |= (1 << i);
        }

        for (long i = 1; i <= B; i++) {
            t = s << 1;
            if(t >= (1 << N)){
                t -= (1 << N);
                t++;
            }
            s ^= t;
            if(visit[s] == 0){
                visit[s] = i;
            } else{
                i += (B - i) / (i - visit[s]) * (i - visit[s]);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if((s & (1 << i)) != 0){
                sb.append(1);
            } else{
                sb.append(0);
            }
            sb.append('\n');
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