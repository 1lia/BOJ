import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long K = Long.parseLong(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringBuilder res = new StringBuilder();

        while(K != 0){
            if((K & 1) == 1){
                sb.append(4);
                K -= 1;
            } else{
                sb.append(7);
                K -= 2;
            }
            K /= 2;
        }

        for(int i = sb.length() - 1 ; i >= 0 ; i--){
            res.append(sb.charAt(i));
        }
        System.out.println(res);
    }
}