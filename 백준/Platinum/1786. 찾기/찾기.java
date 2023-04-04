import java.io.BufferedReader;
import java.io.InputStreamReader;
 
public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String T = bf.readLine();
        String K = bf.readLine();

        KMP(T,K);
        
    }
    public static int[] makeTable(String str) {
    	int size = str.length();
    	int[] table = new int[size];
    	
    	int j = 0;
    	
    	for (int i = 1; i < size; i++) {
			while(j > 0 && str.charAt(i) != str.charAt(j)) {
				j = table[j-1];
			}
			
			if(str.charAt(i) == str.charAt(j)) {
				table[i] = ++j;
			}
		}
    	
    	return table;
    }
    
    public static void KMP(String str , String text) {
    	StringBuilder sb = new StringBuilder("\n");
    	int cnt = 0;
    	int strsize = str.length();
    	int textsize = text.length();
    	int[] table = makeTable(text);
    	
    	int j = 0;
    	
    	for (int i = 0; i < strsize; i++) {
    		while(j > 0 && str.charAt(i) != text.charAt(j)) {
				j = table[j-1];
			}
    		if(str.charAt(i) == text.charAt(j)) {
    			if(j == textsize - 1) {
    				sb.append(i - textsize + 2).append(" ");
    				cnt++;
    				j = table[j];
    			}	else {
    				j++;
    			}
    		}
		}
    	sb.insert(0, cnt);
    	System.out.println(sb.toString());
    }
}