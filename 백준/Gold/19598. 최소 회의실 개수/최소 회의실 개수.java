import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<T> q = new PriorityQueue<>(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                if(o1.e > o2.e){
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        int N = (int) readLong();
        T[] arr = new T[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new T(readLong() , readLong());
        }

        Comparator<T> com = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                if(o1.s > o2.s){
                    return 1;
                } else{
                    return -1;
                }
            }
        };
        Arrays.sort(arr , com);
        long s = 0;
        long res = 0;
        for (int i = 0; i < N; i++) {
            s = arr[i].s;

            q.offer(arr[i]);
            while(!q.isEmpty()){
                if(q.peek().e <= s){
                    q.poll();
                } else{
                    break;
                }
            }
            res = Math.max(res , q.size());
        }

        System.out.println(res);
    }

    public static long readLong() throws Exception {
        long val = 0;
        long c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        boolean flag = (c == '-');
        if (flag) c = System.in.read();
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);

        if (flag) return -val;
        return val;
    }
}

class T{
    long s;
    long e;

    public T(long s, long e) {
        this.s = s;
        this.e = e;
    }
}