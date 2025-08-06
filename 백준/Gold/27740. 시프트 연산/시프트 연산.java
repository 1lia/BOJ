class Main {
    public static void main(String[] args) throws Exception {
        int N = readInt();
        int[] arr = new int[N + 1];
        int l = N;
        int r = 0;
        int min = 301010;
        int max = 0;
        for (int i = 1; i <= N; i++) {
            arr[i] = readInt();
        }
        int res = N;
        for (int i = 1; i <= N; i++) {
            if(arr[i] == 1){
                min = Math.min(min , i);
                max = Math.max(max , i);
            }
        }
        if(max < res){
            res = max;
            l = max;
            r = 0;
        }
        if(N - min + 1 < res){
            res = N - min + 1;
            l = 0;
            r = N - min + 1;
        }
        int old = 0;
        int now = 0;
        for (int i = 1; i <= N; i++) {
            if(arr[i] == 1){
                old = now;
                now = i;
                if(old != 0){
                    if((old << 1) + N - now + 1 < res){
                        res = (old << 1) + N - now + 1;
                        l = old;
                        r = old + N - now + 1;
                    }
                    if(((N - now + 1) << 1) + old < res){
                        res = ((N - now + 1) << 1) + old;
                        l = old + N - now + 1;
                        r = N - now + 1;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(res).append('\n');
        if(l <= r){
            for (int i = 0; i < l; i++) {
                sb.append('L');
            }
            for (int i = 0; i < r; i++) {
                sb.append('R');
            }
        } else{
            for (int i = 0; i < r; i++) {
                sb.append('R');
            }
            for (int i = 0; i < l; i++) {
                sb.append('L');
            }
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