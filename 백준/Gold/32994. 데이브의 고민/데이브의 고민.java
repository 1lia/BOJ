public class Main {
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        int[] arr = {1,3,5,2,4};
        int N = readInt();
        int M = readInt();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int t = (arr[i % 5] + (j % 5));
                if(t > 5){
                    t %= 5;
                }
                sb.append(t).append(' ');
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append('\n');
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
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