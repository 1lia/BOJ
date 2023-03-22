import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception {
		Queue<Truck> q = new ArrayDeque<>();
		int N = readInt();
		int W = readInt();
		int L = readInt();
		int state = 0;
		int time = 0;
		Truck truck = null;
		for (int i = 0; i < N; i++) {
			int v = readInt();
//			더많이 들어가있으면
			if(!q.isEmpty()) {
				if(q.size() >= L) {
					truck = q.poll();
					state -= truck.v;
					time = truck.time + W; //현재시각 갱신
					
					while(state + v > L) {
						truck = q.poll();
						state -= truck.v;
						time = truck.time + W; //현재시각 
					}
					state += v;
					q.offer(new Truck(time , v)); //입장시각 무게
				} else {
					if(q.peek().time + W == time + 1) {
						truck = q.poll();
						state -= truck.v;
						time = truck.time + W; //현재시각 
						
						if(state + v <= L) {
							state += v;
							q.offer(new Truck(time , v));
						} else {
							while(state + v > L) {
								truck = q.poll();
								state -= truck.v;
								time = truck.time + W; //현재시각 
							}
							state += v;
							q.offer(new Truck(time , v)); //입장시각 무게
						}	
					} else if(state + v <= L) {
//						들어갈수있으면
						state += v;
						q.offer(new Truck(++time , v));
					} else {
						while(state + v > L) {
							truck = q.poll();
							state -= truck.v;
							time = truck.time + W; //현재시각 
						}
						state += v;
						q.offer(new Truck(time , v)); //입장시각 무게
					}
				}
			} else {
//				들어갈수있으면 시간을 1초밀고 들어간시간 q에넣음
				state += v;
				q.offer(new Truck(++time , v));
			}
		}
		while(!q.isEmpty()) {
			time = q.poll().time + W;
		}
		System.out.println(time);
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
}

class Truck{
	int time;
	int v;
	public Truck(int time, int v) {
		this.time = time;
		this.v = v;
	}
}