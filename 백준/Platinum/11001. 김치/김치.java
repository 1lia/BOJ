import java.util.ArrayList;

public class Main {
    static int D;
    static int[] T, V, X;

    public static void main(String[] args) throws Exception{
        int N = readInt();
        D = readInt();
        T = new int[N + 1];
        V = new int[N + 1];
        X = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            T[i] = readInt();
        }
        for (int i = 1; i <= N; i++) {
            V[i] = readInt();
        }

        ArrayList<Integer> R = new ArrayList<>();
        ArrayList<Integer> C = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            R.add(i);
            C.add(i);
        }

        smawk(R, C);

        long ans = 0;
        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, f(i, X[i]));
        }
        System.out.println(ans);
    }

    static long f(int x, int y) {
        if (y < x || x + D < y)
            return -Long.MAX_VALUE;
        return 1L * (y - x) * T[y] + V[x];
    }

    static void smawk(ArrayList<Integer> R, ArrayList<Integer> C) {
        int N = R.size(), M = C.size();
        if (N == 0)
            return;
        // REDUCE
        ArrayList<Integer> S = new ArrayList<>();
        for (int it : C) {
            if (S.isEmpty())
                S.add(it);
            else {
                while (!S.isEmpty()) {
                    long p = f(R.get(S.size() - 1), S.get(S.size() - 1));
                    long q = f(R.get(S.size() - 1), it);
                    if (p > q)
                        break;
                    S.remove(S.size() - 1);
                }
                if (S.size() < R.size())
                    S.add(it);
            }
        }
        // INTERPOLATION
        ArrayList<Integer> O = new ArrayList<>();
        for (int i = 1; i < R.size(); i += 2)
            O.add(R.get(i));
        smawk(O, S);
        for (int i = 0, j = 0; i < R.size(); i += 2) {
            int s = S.get(0), e = S.get(S.size() - 1);
            if (0 < i)
                s = X[R.get(i - 1)];
            if (i + 1 < R.size())
                e = X[R.get(i + 1)];
            while (j < S.size() && S.get(j) <= e) {
                if (X[R.get(i)] == 0 || f(R.get(i), X[R.get(i)]) < f(R.get(i), S.get(j)))
                    X[R.get(i)] = S.get(j);
                ++j;
            }
            --j;
        }
    }
    
    public static int readInt() throws Exception {
		int val = 0;
		boolean flag = false;
		do {
			int c = System.in.read();
			if (c == '-') {
				flag = true;
				continue;
			}
			if (c == 13)
				continue;

			if (c == 32 || c == 10)
				break;
			val = 10 * val + c - 48;
		} while (true);
		return flag ? -val : val;
	}
}