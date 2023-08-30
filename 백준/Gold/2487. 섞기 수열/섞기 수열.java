public class Main {
    public static void main(String[] args) throws Exception {
        int N = readInt();
        int[] arr = new int[N+1];
        boolean[] visit = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = readInt();
        }
        int res = 1;
        for (int i = 1; i <= N; i++) {
            if(!visit[i]){
                visit[i] = true;
                int t = arr[i];
                int cnt = 1;
                while(!visit[t]){
                    visit[t] = true;
                    cnt++;
                    t = arr[t];
                }
                if(cnt > 1){
                    int a = res / gcd(res , cnt);
                    res = a * cnt;
                }
            }
        }
        System.out.println(res);
    }

    public static int gcd(int a , int b){
        if(a < b){
            a ^= b; b ^= a; a ^= b;
        }
        while(b != 0){
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
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