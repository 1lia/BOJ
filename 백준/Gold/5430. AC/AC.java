import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Deque<Integer> q = new ArrayDeque<Integer>();
		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			int flag = 0;
			String p = br.readLine();
			int N = Integer.parseInt(br.readLine());

			String s = br.readLine();
			s = s.substring(1, s.length() - 1);
			String[] arr = s.split(",");

			q.clear();
			if(s.length() != 0) {
				for (int j = 0; j < arr.length; j++) {
					q.offer(Integer.parseInt(arr[j]));
				}
			}
	
			for (int j = 0; j < p.length(); j++) {
				if(p.charAt(j) == 'R') {
					flag = flag ^ 1;
				} else {
					if(q.isEmpty()) {
						flag = -1;
						break;
					}
					if(flag == 1) {
						q.pollLast();
					} else {
						q.pollFirst();
					}
				}
			}
			
			if(!q.isEmpty()) {
				sb.append('[');
				while(!q.isEmpty()) {
					if(flag == 1)
						sb.append(q.pollLast()).append(',');
					else
						sb.append(q.pollFirst()).append(',');;
					
				}
				sb.deleteCharAt(sb.length() - 1);
				sb.append(']').append('\n');
			} else if (flag == -1){
				sb.append("error").append('\n');
			} else {
				sb.append("[]").append('\n');
			}
		}

		System.out.println(sb.toString());
	}
}