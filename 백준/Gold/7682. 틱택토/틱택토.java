//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//
//public class Main {
//    public static void main(String[] args) throws Exception{
//    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    	StringBuilder sb = new StringBuilder();
//    	
//    	while(true) {
//    		String s = br.readLine();
//    		if(s.equals("end"))
//    			break;
//    		
//    		int cntx = 0;
//        	int cnto = 0;
//        	int linex = 0;
//        	int lineo = 0;
////    		xo개수세기
//    		for (int i = 0; i < 9; i++) {
//				if(s.charAt(i) == 'X')
//					cntx++;
//				else if(s.charAt(i) == 'O')
//					cnto++;
//			}
//    		
////    		o가 더많거나 x 가 o보다 2개더많으면안됨
//    		if(cnto > cntx || cntx - cnto > 1) {
//    			sb.append("invalid").append('\n');
//    		}
//    		
//    	}
//    	System.out.println(sb.toString());
//    }
//}
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String s = br.readLine();
			int xcnt = 0;
			int ocnt = 0;
			int all = 0;
			char c = 0;
			int cnt = 0;
			int xallcnt = 0;
			int oallcnt = 0;
			
			if(s.equals("end"))
				break;
			
			for (int i = 0; i < 9; i++) {
				if(s.charAt(i) == 'X') {
					xcnt++;
				} else if(s.charAt(i) == 'O') {
					ocnt++;
				}
			}
			if(ocnt > xcnt || xcnt - ocnt > 1 || (xcnt < 3 && ocnt < 3)) {
				sb.append("invalid").append('\n');
			} else {
//				가로
				for (int i = 0; i < 9; i++) {
					if(i % 3 == 0) {
						c = s.charAt(i);
						if(c == '.')
							cnt = -1;
						else
							cnt = 0;
					} else {
						if(s.charAt(i) == c)
							cnt++;
						if(cnt == 2) {
							if(s.charAt(i) == 'X')
								xallcnt++;
							else
								oallcnt++;
							all++;
						}
					}
				}
//				세로
				int i = 0;
				while(true) {
					if(i < 3) {
						c = s.charAt(i);
						if(c == '.')
							cnt = -1;
						else
							cnt = 0;
					} else {
						if(s.charAt(i) == c)
							cnt++;
						if(cnt == 2) {
							if(s.charAt(i) == 'X')
								xallcnt++;
							else
								oallcnt++;
							all++;
						}
					}
					i += 3;
					if(i == 11)
						break;
					if(i != 8)
						i %= 8;	
				}
				if(s.charAt(0) == s.charAt(4) && s.charAt(4) == s.charAt(8) && s.charAt(0) != '.') {
					if(s.charAt(0) == 'X')
						xallcnt++;
					else
						oallcnt++;
					all++;
				}
				if(s.charAt(2) == s.charAt(4) && s.charAt(4) == s.charAt(6) && s.charAt(2) != '.') {
					if(s.charAt(2) == 'X')
						xallcnt++;
					else
						oallcnt++;
					all++;
				}
				if(all == 1) {
					if(xcnt - ocnt == 1 && xallcnt == 1) {
						sb.append("valid").append('\n');
					} else if(xcnt == ocnt && oallcnt == 1){
						sb.append("valid").append('\n');
					} else {
						sb.append("invalid").append('\n');
					}
				} else if(all == 2 && xallcnt == 2 && xcnt == 5 && ocnt == 4) {
					sb.append("valid").append('\n');
				} else if(all == 0 && xcnt + ocnt == 9) {
					sb.append("valid").append('\n');
				} else {
					sb.append("invalid").append('\n');
				}
			}
		}
		
		System.out.println(sb.toString());
	}
}