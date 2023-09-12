import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws Exception {
        int N = readInt();
        StringBuilder sb = new StringBuilder();
        HashMap<Integer , Integer> hashMap = new HashMap<>();
        Trie trie = new Trie();
        trie.insert(0);
        for (int i = 0; i < N; i++) {
            int a = readInt();
            int x = readInt();
            if(a == 1){
                if(hashMap.containsKey(x)){
                    int cnt = hashMap.get(x);
                    hashMap.put(x , cnt + 1);
                    if(cnt == 0){
                        trie.insert(x);
                    }
                } else{
                    hashMap.put(x , 1);
                    trie.insert(x);
                }
            } else if(a == 2){
                int cnt = hashMap.get(x);
                hashMap.put(x , cnt-1);
                if(cnt == 1){
                    trie.delete(x,0 , 1073741824);
                }
            } else{
                sb.append(trie.query(x)).append('\n');
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
        if (flag) c = System.in.read();
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);

        if (flag) return -val;
        return val;
    }
}

class Trie {
    int[][] trie = new int[5000000][2];
    int piv = 0;

    void insert(int n) {
        int p = 0;
        int x = 1073741824;
        while(x != 0){
            if((x & n) == 0){
                if(trie[p][0] == 0){
                    trie[p][0] = ++piv;
                }
                p = trie[p][0];
            } else {
                if(trie[p][1] == 0){
                    trie[p][1] = ++piv;
                }
                p = trie[p][1];
            }
            x >>= 1;
        }
    }

    void delete(int n , int p , int x){
        if(x != 0){
            if((x & n) == 0){
                delete(n , trie[p][0] , x >> 1);
                if(trie[trie[p][0]][0] == 0 &&  trie[trie[p][0]][1] == 0){
                    trie[p][0] = 0;
                }
            } else{
                delete(n , trie[p][1] , x >> 1);
                if(trie[trie[p][1]][0] == 0 &&  trie[trie[p][1]][1] == 0){
                    trie[p][1] = 0;
                }
            }
        }
    }

    int query(int n) {
        int p = 0;
        int res = 0;
        int x = 1073741824;
        while(x != 0){
            if((x & n) == 0){
                if(trie[p][1] != 0){
                    p = trie[p][1];
                    res += x;
                } else{
                    p = trie[p][0];
                }

            } else {
                if(trie[p][0] != 0){
                    p = trie[p][0];
                    res += x;
                } else{
                    p = trie[p][1];
                }
            }
            x >>= 1;
        }
        return res;
    }
}