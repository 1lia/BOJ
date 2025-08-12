import java.util.*;

class Main {
    public static int[] arr;
    public static int N , min , max;

    public static void main(String[] args) throws Exception {
        min = Integer.MAX_VALUE;
        max = -Integer.MAX_VALUE;
        N = readInt();
        arr = new int[N];
        int[] pi = new int[4];
        for (int i = 0; i < N; i++) {
            arr[i] = readInt();
        }
        for (int i = 0; i < 4; i++) {
            pi[i] = readInt();
        }
        dfs(pi[0],pi[1],pi[2],pi[3],0);
        System.out.println(max);
        System.out.println(min);
    }

    public static void dfs(int plus , int minus , int mul , int div , long s){
        if(plus + minus + mul + div == 0){
            run(s);
            return;
        }
        if(plus != 0){
            dfs(plus - 1,minus,mul,div,s * 10 + 1);
        }
        if(minus != 0){
            dfs(plus,minus - 1,mul,div,s * 10 + 2);
        }
        if(mul != 0){
            dfs(plus,minus,mul - 1,div,s * 10 + 3);
        }
        if(div != 0){
            dfs(plus,minus,mul,div - 1,s * 10 + 4);
        }
    }

    private static void run(long s){
        Deque<Integer> q = new ArrayDeque<>();
        Deque<Long> q2 = new ArrayDeque<>();
        q.offerFirst(arr[0]);
        for (int i = 1; i < N; i++) {
            long t = s % 10;
            s /= 10;
            if(t == 1 || t == 2){
                q.offerFirst(arr[i]);
                q2.offerFirst(t);
            } else{
                if(t == 3){
                    q.offerFirst(q.pollFirst() * arr[i]);
                } else {
                    q.offerFirst(q.pollFirst() / arr[i]);
                }
            }
        }
        int res = 0;
        while(!q2.isEmpty()){
            if(q2.pollFirst() == 1){
                res += q.pollFirst();
            } else{
                res -= q.pollFirst();
            }
        }
        res += q.pollFirst();
        max = Math.max(max , res);
        min = Math.min(min , res);
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