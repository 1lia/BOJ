import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		int N = readInt();
		int[] arr = new int[N];
		int[] base = new int[N << 1];
		int[] query = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = readInt();
		}
		Arrays.sort(arr);
		base[0] = 360000 - arr[N-1] + arr[0]; 
		for (int i = 1; i < N; i++) {
			base[i] = arr[i] - arr[i-1];
		}
		for (int i = N; i < N << 1; i++) {
			base[i] = base[i-N];
		}
		for (int i = 0; i < N; i++) {
			arr[i] = readInt();
		}
		Arrays.sort(arr);
		
		query[0] = 360000 - arr[N-1] + arr[0]; 
		for (int i = 1; i < N; i++) {
			query[i] = arr[i] - arr[i-1];
		}
		
		System.out.println(new Kmp().run(base, query));
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

class Kmp{
    public String run(int[] all , int[] s){
        int allSize = all.length;
        int size = s.length;
        int[] table = makeTable(s);

        for (int i = 0 , j = 0 ; i < allSize; i++) {
            while(j > 0 && all[i] != s[j]) {
                j = table[j-1];
            }
            if(all[i] == s[j]) {
                if(j == size - 1) {
                	return "possible";
                }	else {
                    j++;
                }
            }
        }
        return "impossible";
    }

    public static int[] makeTable(int[] s) {
        int[] table = new int[s.length];
        for (int i = 1 , j = 0; i < s.length; i++) {
            while(j > 0 && s[i] != s[j]) {
                j = table[j-1];
            }

            if(s[i] == s[j]) {
                table[i] = ++j;
            }
        }
        return table;
    }
}