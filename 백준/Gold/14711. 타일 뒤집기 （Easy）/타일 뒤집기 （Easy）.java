import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        if(N == 1) {
            System.out.println(str);
            return;
        }

        sb.append(str).append('\n');
        int[][] arr = new int[N][N];
        int[][] temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            if(str.charAt(i) == '#') {
                arr[0][i] = 1;
                temp[0][i] += 1;
                temp[1][i] += 1;
                if(i - 1 >= 0)
                    temp[0][i - 1] += 1;
                if(i + 1 < N)
                    temp[0][i + 1] += 1;
            }
        }
        for (int i = 0; i < N; i++) {
            arr[0][i] += temp[0][i];
        }

        for (int i = 1; i + 1 < N; i++) {
            for (int j = 0; j < N; j++) {
                if((arr[i - 1][j] & 1) == 1) {
                    arr[i][j] = 1;
                    temp[i][j] += 1;
                    temp[i + 1][j] += 1;
                    if(j - 1 >= 0)
                        temp[i][j - 1] += 1;
                    if(j + 1 < N)
                        temp[i][j + 1] += 1;
                    sb.append('#');
                } else{
                    sb.append('.');
                }
            }
            for (int j = 0; j < N; j++) {
                arr[i][j] += temp[i][j];
            }
            sb.append('\n');
        }
        for (int i = 0; i < N; i++) {
            if((arr[N - 2][i] & 1) == 1)
                sb.append('#');
            else  sb.append('.');
        }
        System.out.println(sb);
    }
}