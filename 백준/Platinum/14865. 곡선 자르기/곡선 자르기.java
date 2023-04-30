import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); //개수입력받음
		int cnt = 0;
		int dx = 0;
		int dy = 0;
		int start = Integer.MAX_VALUE;
		int end = 0;
		int temp = 0;
		int endtemp = Integer.MAX_VALUE;
		LinkedList<Pos> list = new LinkedList<Pos>(); 
		int[][] te = new int[2][N];
		
//		첫번째꺼에서 양수로 시작하므로 뒤로가서 음수일때를 찾아줌
	
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()); //입력 첫번째
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			te[0][i] = x;
			te[1][i] = y;
			
//			양수로 올라갈때시작좌표
			if(dy < 0 && y > 0)
				start = x;
			else if(dy > 0 && y < 0) { //내려갈때 
				if(start == Integer.MAX_VALUE) { //아직 시작점이 위로 안올라오고 내려가기만 했으면 기록
					endtemp = x;
				} else {
					end = x;
					if(start < end)
						list.add(new Pos(start , end));
					else
						list.add(new Pos(end , start));
				}

			}
			dx = x;
			dy = y;
		}
//		양수로 시작하고 끝점이 양수이면 이미 start가 갱신된거임
		if(endtemp != Integer.MAX_VALUE && te[1][N-1] > 0) {
			if(start < endtemp)
				list.add(new Pos(start , endtemp));
			else
				list.add(new Pos(endtemp , start));
		} else if(endtemp != Integer.MAX_VALUE && te[1][N-1] < 0) { //끝점이 음수이면 마지막 x가 갱신값
			if(te[0][N-1] < endtemp) {
				list.add(new Pos(te[0][N-1] , endtemp));
			} else
				list.add(new Pos(endtemp , te[0][N-1]));
		} else if(te[1][N-1] > 0 && te[1][0] < 0) { //끝점의 y값이 양수이고 첫점이 음수이면 end가한번 안끝났음
			end = te[0][0];
			if(start < end)
				list.add(new Pos(start , end));
			else
				list.add(new Pos(end , start));
		}
		
		Pos[] arr = list.toArray(new Pos[list.size()]);
		Arrays.sort(arr);
		int result1 = 1;
		int result2 = 1;
		int endy = arr[0].y;
		for (int i = 1; i < arr.length; i++) {
			if(arr[i].y > endy) {
				result1++;
				endy = arr[i].y;
			}
			
			if(arr[i-1].y < arr[i].y) { //전꺼보다크면 전꺼는 됨 되는거
				result2++;
			}
		}
		System.out.println(result1 + " " + result2);
	}
}

class Pos implements Comparable<Pos>{
	int x;
	int y;
	
	Pos(int x , int y){
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Pos o) {
		if(this.x < o.x) {
			return -1;
		} else {
			return 1;
		}	
	}
}