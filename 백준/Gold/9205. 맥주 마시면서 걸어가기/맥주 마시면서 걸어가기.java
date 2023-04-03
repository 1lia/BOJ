import java.util.PriorityQueue;

public class Main {
	public static Pos end;
    public static void main(String[] args) throws Exception{
    	PriorityQueue<Pos> q = new PriorityQueue<>();
    	int T = readInt();
    	StringBuilder sb = new StringBuilder();
    	
    	for (int z = 0; z < T; z++) {
			int N = readInt();
			Pos[] arr = new Pos[N+2];
			boolean[] visit = new boolean[N+2];
			boolean flag = false;
			for (int i = 0; i - 2 < N; i++) {
				arr[i] = new Pos(readInt(), readInt());
			}
			end = arr[N+1];
			q.clear();
			q.add(arr[0]);
			visit[0] = true;
			while(!q.isEmpty()) {
				Pos pos = q.poll();
				if(end.x == pos.x && end.y == pos.y) {
					flag = true;
					sb.append("happy").append('\n');
					break;
				}
				
				
				for (int i = 0; i - 2 < N; i++) {
					if(!visit[i] && distance(pos , arr[i])) {
						visit[i] = true;
						q.offer(arr[i]);
					}
				}
			}
			if(!flag) {
				sb.append("sad").append('\n');
			}
	
		}
    	System.out.println(sb.toString());
    }
    public static boolean distance(Pos p1 , Pos p2) {
    	if(Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y) <= 1000) {
    		return true;
    	}
    	return false;
    }
    
    public static int readInt() throws Exception {
		int val = 0;
		boolean flag = false;
		do {
			int c = System.in.read();
			if (c == '-') {
				flag = true;
				continue;
			}
			if (c == 13)
				continue;

			if (c == 32 || c == 10)
				break;
			val = 10 * val + c - 48;
		} while (true);
		return flag ? -val : val;
	}
    
    static class Pos implements Comparable<Pos>{
    	int x;
    	int y;
    	public Pos(int x, int y) {
    		this.x = x;
    		this.y = y;
    	}

		@Override
		public int compareTo(Pos o) {
			int a = Math.abs(this.x - end.x) + Math.abs(this.y - end.y);
			int b = Math.abs(o.x - end.x) + Math.abs(o.y - end.y);
			return a - b;
		}
    }
}

