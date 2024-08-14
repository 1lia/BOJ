class Main {
    public static void main(String[] args) throws Exception {
        int N = readInt();
        int[] arr = new int[N];
        int[] l = new int[N];
        int[] r = new int[N];
        int res = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = readInt();
        }
        l[0] = arr[0];
        r[N - 1] = arr[N - 1];
        for (int i = 1; i < N; i++) {
            l[i] = Math.max(arr[i] , l[i - 1]);
        }

        for (int i = N - 2; i >= 0; i--) {
            r[i] = Math.max(arr[i] , r[i + 1]);
        }

        for (int i = 1; i < N; i++) {
            if(l[i-1] > r[i]){
                res++;
            } else if(l[i-1] < r[i]){
                res--;
            }
        }

        if(res > 0){
            System.out.println("R");
        } else if(res < 0){
            System.out.println("B");
        } else{
            System.out.println("X");
        }
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