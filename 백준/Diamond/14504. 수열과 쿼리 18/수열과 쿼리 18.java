import java.util.Arrays;

public class Main{
	public static int div;
	public static Pos[] arr;
	public static int N;
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		N = readInt();
		div = (int) Math.sqrt(N);
		arr = new Pos[N];
		int i , j , k , s , e , start , end , cnt , result = 0;
		for (int z = 0; z < N; z++) {
			arr[z] = new Pos(z , readInt());
		}
		Arrays.sort(arr);

		int Q = readInt();
		for (int z = 0; z < Q; z++) {
			int flag = readInt();
			if(flag == 1) {
				i = readInt() - 1;
				j = readInt() - 1;
				k = readInt();
				
				start = i / div;
				end = j / div;
				
				cnt = start;
				result = 0;
				s = start * div;
				e = (start + 1) * div;
				if(e > N) {
					e = N;
				}
				
				for (int l = s; l < e; l++) {
					if(arr[l].index >= i && arr[l].index <= j && arr[l].v > k) {
						result++;
					}
				}

				for (int l = start + 1; l < end; l++) {
					result += upper_bound(l * div , (l+1) * div - 1 , k);
				}

				s = end * div;
				e = (end + 1) * div;
				if(e > N) {
					e = N;
				}
				
				if(start != end) {
					for (int l = s; l < e; l++) {
						if(arr[l].index >= i && arr[l].index <= j && arr[l].v > k) {
							result++;
						}
					}
				}
				sb.append(result).append('\n');
			} else{
				i = readInt() - 1;
				k = readInt();
				start = i / div;
				end = i / div;
//				System.out.println(start + " " + end);
				s = start * div;
				e = (end + 1) * div;
				if(e > N) {
					e = N;
				}
//				System.out.println(s + " " + e);
				for (int l = s; l < e; l++) {
					if(arr[l].index == i) {
						arr[l].v = k;
						break;
					}
				}
				Arrays.sort(arr ,s, e);
			}
//			System.out.println(arr[0].index + " " + arr[0].v);
//			System.out.println(arr[1].index + " " + arr[1].v);
//			System.out.println(arr[2].index + " " + arr[2].v);
		}
		System.out.println(sb.toString());
	}
	
	public static int upper_bound(int s , int e , int k) {
		int start = s;
		int end = e;
		int mid = 0;
		while(start < end) {
			mid = (end + start) >> 1;
			if (arr[mid].v <= k) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		
		if(arr[start].v <= k) {
			start++;
		}
	
		return e - start + 1;
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
		int index;
		int v;
		
		public Pos(int index, int v) {
			this.index = index;
			this.v = v;
		}
		@Override
		public int compareTo(Pos o) {
			if(this.index / div < o.index / div) {
				return -1;
			} else if(this.index / div == o.index / div) {
				if(this.v < o.v) 
					return -1;
				else if(this.v == o.v)
					return 0;
				else
					return 1;
			}
			return 1;
		}
	}
}

