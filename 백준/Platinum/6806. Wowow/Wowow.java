import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        int N = readInt();
        int[][] Query = new int[N][3];
        ArrayList<Integer> arr = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        HashMap<Integer , Integer> map = new HashMap<>();

        int[] friend = new int[1000011];

        for (int i = 0; i < N; i++) {
            Query[i][0] = readInt();
            Query[i][1] = readInt();
            if(Query[i][0] != 33){
                Query[i][2] = readInt();
                if(!set.contains(Query[i][2])){
                    set.add(Query[i][2]);
                    arr.add(Query[i][2]);
                }
            }
        }
        Collections.sort(arr);
        int size = arr.size();
        int[] index = new int[size + 1];
        for (int i = 0; i < size; i++) {
            map.put(arr.get(i) , i + 1);
        }
        SegmentTree seg = new SegmentTree(size);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if(Query[i][0] == 33){ //Q
                sb.append(index[seg.query(1,1,size,Query[i][1])]).append('\n');
            } else if(Query[i][0] == 30){ //M
                int idx = map.get(Query[i][2]);
                seg.update(1,1,size, idx , 1);
                friend[Query[i][1]] = idx;
                index[idx] = Query[i][1];
            } else{ //N
                int idx = map.get(Query[i][2]);
                seg.update(1,1,size,friend[Query[i][1]],0);
                seg.update(1,1,size,idx,1);
                friend[Query[i][1]] = idx;
                index[idx] = Query[i][1];
            }
        }
        System.out.println(sb);
    }

    static class SegmentTree{
        private int[] cnt;
        SegmentTree(int n){
            double treeh = Math.ceil(Math.log(n) / Math.log(2)) + 1;
            long treesize = Math.round(Math.pow(2, treeh));
            cnt = new int[Math.toIntExact(treesize)];
        }

        void update(int node , int start , int end , int index , int p) {
            if(index < start || end < index)
                return;

            if(index == start && end == index) {
                cnt[node] = p;
            } else {
                int mid = (start + end) >> 1;
                int next = node << 1;
                update(next , start , mid , index , p);
                update(next + 1 , mid + 1 , end , index , p);
                cnt[node] = cnt[next] + cnt[next + 1];
            }
        }

        int query(int node , int start , int end , int K) {
            if(start == end)
                return start;
            int mid = (start + end) >> 1;
            int next = node << 1;
            if(cnt[next + 1] < K){
                return query(next , start , mid , K - cnt[next + 1]);
            } else{
                return query(next + 1 , mid + 1 , end , K);
            }
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
