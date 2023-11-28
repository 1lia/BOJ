import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws Exception {
        new Mos().run();
    }
}

class Mos{
    int N;
    int Q;
    int div;
    int idx;
    int[] arr;
    int[] cnt;
    int[] res;
    Query[] query;
    HashMap<Integer,Integer> hash;

    public void run() {
        StringBuilder sb = new StringBuilder();
        init();
        Arrays.sort(query);
        int s = 1;
        int e = 1;
        cnt[arr[1]]++;
        int rr = 1;
        for (int i = 0; i < Q; i++) {
            while(e < query[i].e){
                int a = arr[++e];
                cnt[a]++;
                if(cnt[a] == 1)
                    rr++;
            }
            while(e > query[i].e){
                int a = arr[e--];
                cnt[a]--;
                if(cnt[a] == 0)
                    rr--;
            }

            while(s < query[i].s){
                int a = arr[s++];
                cnt[a]--;
                if(cnt[a] == 0)
                    rr--;
            }

            while(s > query[i].s){
                int a = arr[--s];
                cnt[a]++;
                if(cnt[a] == 1)
                    rr++;
            }
            res[query[i].idx] = rr;
        }
        for (int i = 0; i < Q; i++) {
            sb.append(res[i]).append('\n');
        }
        System.out.println(sb);
    }

    private void init(){
        idx = 1;
        try {
            N = readInt();
            div = (int) Math.sqrt(N);
            arr = new int[N+1];

            hash = new HashMap<>();
            for (int i = 1; i <= N; i++) {
                int a = readInt();
                if(!hash.containsKey(a)) {
                    hash.put(a, idx);
                    arr[i] = idx;
                    idx++;
                } else {
                    arr[i] = hash.get(a);
                }
            }
            cnt = new int[idx];
            Q = readInt();
            res = new int[Q];
            query = new Query[Q];
            for (int i = 0; i < Q; i++) {
                query[i] = new Query(readInt(), readInt() , i);
            }
        } catch (Exception e) {

        }
    }


    class Query implements Comparable<Query>{
        int s;
        int e;
        int idx;
        public Query(int s, int e , int idx) {
            this.s = s;
            this.e = e;
            this.idx = idx;
        }
        @Override
        public int compareTo(Query o) {
            if(this.s / div == o.s / div) {
                return this.e - o.e;
            }
            return this.s - o.s;
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