import java.io.*;

class Main {
    public static int x , y , range;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            if(s.equals("W")){
                move(0);
            } else if(s.equals("A")){
                move(3);
            } else if(s.equals("S")){
                move(2);
            } else if(s.equals("D")){
                move(1);
            } else if(s.equals("MR")){
                range++;
                range %= 4;
            } else if(s.equals("ML")){
                range--;
                range += 4;
                range %= 4;
            }
            sb.append(x).append(' ').append(y).append(' ');
            if(range == 0){
                sb.append(x).append(' ').append(y - 1).append('\n');
            } else if(range == 1){
                sb.append(x - 1).append(' ').append(y).append('\n');
            } else if(range == 2){
                sb.append(x).append(' ').append(y + 1).append('\n');
            } else if(range == 3){
                sb.append(x + 1).append(' ').append(y).append('\n');
            }
        }
        System.out.print(sb);
    }
    public static void move(int t){
        int rr = (range + t) % 4;
        if(rr == 0){
            y++;
        } else if(rr == 1){
            x++;
        } else if(rr == 2){
            y--;
        } else if(rr == 3){
            x--;
        }
    }
}