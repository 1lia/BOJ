import java.io.*;
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
        long[] num = {2, 6, 30, 210, 2310, 30030, 510510, 9699690, 223092870, 6469693230L, 200560490130L, 7420738134810L, 304250263527210L, 13082761331670030L, 614889782588491410L};
        String[] res = {"1/2","2/3","11/15","27/35","61/77","809/1001","13945/17017","268027/323323","565447/676039","2358365/2800733","73551683/86822723","2734683311/3212440751","112599773191/131710070791","4860900544813/5663533044013","9968041656757/11573306655157"};
        for (int i = 14; i >= 0; i--) {
            if(N >= num[i]){
                System.out.println(res[i]);
                return;
            }
        }
    }
}