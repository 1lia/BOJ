class Main {
    public static void main(String[] args) throws Exception {
        int L = readInt();
        int N = readInt();
        int T = readInt();
        int[] arr = new int[L + 1];
        Pos[] bo = new Pos[N];
        int res = 0;
        for (int i = 0; i < N; i++) {
            bo[i] = new Pos(readInt() , 1);
            if(readInt() == 34)
                bo[i].rr = 1;
            else
                bo[i].rr = -1;
        }

        for (int i = 1; i <= T; i++) {
            for (int j = 0; j < N; j++) {
                bo[j].idx += bo[j].rr;
                if(bo[j].idx == 0){
                    bo[j].rr = 1;
                } else if(bo[j].idx == L){
                    bo[j].rr = -1;
                }
                if(arr[bo[j].idx] == i){
                    res++;
                }
                arr[bo[j].idx] = i;
            }
        }
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

class Pos{
    int idx;
    int rr;

    public Pos(int idx, int rr) {
        this.idx = idx;
        this.rr = rr;
    }
}