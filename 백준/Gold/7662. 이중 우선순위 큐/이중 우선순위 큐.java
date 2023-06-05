import java.util.HashMap;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws Exception {
		int T = readInt();
		StringBuilder sb = new StringBuilder();
		HashMap<Integer, Integer> map = new HashMap<>();
		TreeSet<Integer> set = new TreeSet<>();
		
		for (int z = 0; z < T; z++) {
			int N = readInt();
			set.clear();
			map.clear();
			for (int i = 0; i < N; i++) {
				int c = readInt();
				int a = readInt();
				
				if(c == 25) { // i
					if(map.containsKey(a)) {
						map.put(a, map.get(a)+1);
					} else {
						set.add(a);
						map.put(a, 1);
					}
				} else {
					if(set.size() == 0)
						continue;
					int s = 0;
					if(a == 1) {
						s = set.last();
					} else {
						s = set.first();
					}
					 
					int v = map.get(s);
					if(v == 1) {
						map.remove(s);
						if(a == 1) {
							set.pollLast();
						} else {
							set.pollFirst();
						}
					} else {
						map.put(s, v-1);
					}
				}
			}
			if(set.isEmpty()) {
				sb.append("EMPTY").append('\n');
			} else {
				sb.append(set.last()).append(' ').append(set.first()).append('\n');
			}
		}
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