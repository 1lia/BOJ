import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws Exception {
        int N = readInt();
        int M = readInt();
        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int a = readInt();
            if(a < 0){
                arr1.add(-a);
            } else{
                arr2.add(a);
            }
        }
        Collections.sort(arr1 , Collections.reverseOrder());
        Collections.sort(arr2 , Collections.reverseOrder());
        int o1 = 0;
        if(arr1.size() != 0){
            o1 = arr1.get(0);
        }
        int o2 = 0;
        if(arr2.size() != 0){
            o2 = arr2.get(0);
        }
        int s = M;
        int res = o1+o2;
        while(arr1.size() > s){
            res += (arr1.get(s) << 1);
            s += M;
        }
        s = M;
        while(arr2.size() > s){
            res += (arr2.get(s) << 1);
            s += M;
        }
        System.out.println(res + Math.min(o1 , o2));
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