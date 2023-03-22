import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	char[] s1 = br.readLine().toCharArray();
    	char[] s2 = br.readLine().toCharArray();
    	Arrays.sort(s1);
    	Arrays.sort(s2);
    	int N = s2.length;
    	char temp;
    	for (int i = 0; i < N/2; i++) {
    		temp = s2[i];
    		s2[i] = s2[N-1-i];
    		s2[N-1-i] = temp;
		}
    	char[] result = new char[N];
    	int cnt1 = 0;
    	int cnt2 = 0;
    	int start = 0;
    	int end = N-1;
    	while(start != end + 1) {
//    		내차례
    		if(s1[cnt1] < s2[cnt2]) {
    			result[start++] = s1[cnt1++];
    		} else {
    			result[end]  = s1[cnt1 + ((end - start) >> 1)];
    			end--;
    		}
    		
    		if(start == end + 1)
    			break;
//    		상대방 차례
    		if(s2[cnt2] > s1[cnt1]) {
    			result[start++] = s2[cnt2++];
    		} else {
    			result[end]  = s2[cnt2 + ((end - start) >> 1)];
    			end--;
    		}
    	}
    	System.out.println(new String(result));
    }
}