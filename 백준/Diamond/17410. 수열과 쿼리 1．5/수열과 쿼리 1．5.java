import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static int div;
	public static Pos[] arr;
	public static int N;
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		FastIO fast = new FastIO();
		N = fast.nextInt();
		div = (int) Math.round(Math.sqrt(N));
		arr = new Pos[N];
		int i , j , k , s , e , start , end , cnt , result = 0;
		for (int z = 0; z < N; z++) {
			arr[z] = new Pos(z , fast.nextInt());
		}
		Arrays.sort(arr);

		int Q = fast.nextInt();
		for (int z = 0; z < Q; z++) {
			int flag = fast.nextInt();
			if(flag == 2) {
				i = fast.nextInt() - 1;
				j = fast.nextInt() - 1;
				k = fast.nextInt();
				
				start = i / div;
				end = j / div;
				
				cnt = start;
				result = 0;
				s = start * div;
				e = (start + 1) * div;
				if(e > N) {
					e = N;
				}
				
				s = upper_bound_start(s, e - 1, k);
				for (int l = s; l < e; l++) {
					if(arr[l].index >= i && arr[l].index <= j) {
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
					s = upper_bound_start(s, e - 1, k);
					for (int l = s; l < e; l++) {
						if(arr[l].index >= i && arr[l].index <= j) {
							result++;
						}
					}
				}
				sb.append(result).append('\n');
			} else{
				i = fast.nextInt() - 1;
				k = fast.nextInt();
				start = i / div;
				end = i / div;
				s = start * div;
				e = (end + 1) * div;
				if(e > N) {
					e = N;
				}
				for (int l = s; l < e; l++) {
					if(arr[l].index == i) {
//						여기서 k의 upper_bound_start 를 찾아서 돌려줌
						if(arr[l].v < k) {
							int t = upper_bound_start(l,e-1,k);
							Pos d = arr[l];
							
							for (int m = l; m < t-1; m++) {
								arr[m] = arr[m+1];
							}
							d.v = k;
							arr[t-1] = d;
//							위에서 찾고 t-1에 넣어주고 l ~ t - 1 까지 배열복사 
						} else if(arr[l].v > k) {
							int t = upper_bound_start(s,l,k);
							Pos d = arr[l];
							for (int m = l; m > t; m--) {
								arr[m] = arr[m-1];
							}
							d.v = k;
							arr[t] = d;
						}
						break;
					}
				}
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
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
	
	public static int upper_bound_start(int s , int e , int k) {
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
		return start;
	}

	private static class FastIO {
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;

		private byte[] inbuffer;
		private int inbufferpointer, bytesread;
		
		FastIO() {
			din = new DataInputStream(System.in);
			inbuffer = new byte[BUFFER_SIZE];
		}
		
		public int nextInt() throws Exception { 
            int ret = 0; 
            byte b = read(); 
            while (b <= ' ') 
                b = read(); 
            boolean isneg = (b == '-'); 
            if (isneg) 
                b = read(); 
            do { 
                ret = ret * 10 + b - '0';
            } while ((b = read()) >= '0' && b <= '9'); 
  
            if (isneg) 
                return -ret; 
            return ret; 
        }
		
		private byte read() throws Exception { 
            if (inbufferpointer == bytesread) 
                fillbuffer(); 
            return inbuffer[inbufferpointer++]; 
        }
		private void fillbuffer() throws Exception { 
            bytesread = din.read(inbuffer, inbufferpointer = 0, BUFFER_SIZE); 
            if (bytesread == -1) 
                inbuffer[0] = -1; 
        }
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