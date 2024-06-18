import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		String s = new BufferedReader(new InputStreamReader(System.in)).readLine();
		int l = s.length() - 3;
		if(s.charAt(l) == 'e' && s.charAt(l + 1) == 'h' && s.charAt(l + 2) == '?')
			System.out.println("Canadian!");
		else
			System.out.println("Imposter!");
			
	}
}