import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		Deque<Pos> q = new ArrayDeque<>();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int a = 0;
		st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(bf.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			a = Integer.parseInt(st.nextToken());
			while(!q.isEmpty() && q.peekLast().num >= a) {
				q.pollLast();
			}
			q.addLast(new Pos(a , i));
			if(!q.isEmpty() && q.peekFirst().count <= i - L) {
				q.pollFirst();
			}
			sb.append(q.peekFirst().num).append(" ");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}

class Pos{
	int num;
	int count;
	Pos(int num , int count){
		this.num = num;
		this.count = count;
	}
}