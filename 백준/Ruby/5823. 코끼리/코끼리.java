import java.util.HashMap;
import java.util.TreeSet;

public class Main {
	public static TreeSet<Long>[] yes;
	public static TreeSet<Long>[] not;
	public static HashMap<Long, Integer> map;
	public static int div = 31623 , res = 0;
	public static long L;
	
    public static void main(String[] args) throws Exception {
    	StringBuilder sb = new StringBuilder();
    	map = new HashMap<>();
    	yes = new TreeSet[div];
    	not = new TreeSet[div];
    	for (int i = 0; i < div; i++) {
    		yes[i] = new TreeSet<>();
    		not[i] = new TreeSet<>();
		}
        int N = readInt();
        L = readLong();
        int M = readInt();
        long size = -1;
        long[] arr = new long[N];

        for (int i = 0; i < N; i++) {
            arr[i] = readLong();
            map.put(arr[i], 1);
            if(arr[i] <= size) {
            	not[(int) (arr[i] / div)].add(arr[i] % div);
            } else {
            	res++;
            	size = arr[i] + L;
            	yes[(int) (arr[i] / div)].add(arr[i] % div);
            }
        }
        
        for (int i = 0; i < M; i++) {
            int a = readInt();
            long b = readLong();
            Long old = arr[a];
            long now = arr[a] = b;
            int quotient = (int) (old / div);
            long remainder = old % div;
            if(map.get(old) == 1) {
            	map.remove(old);
            	if(yes[quotient].contains(remainder)){
            		res--;
                	yes[quotient].remove(remainder);
                	NotToYes(old , old + L);
                } else {
                	not[quotient].remove(remainder);
                }
            } else {
            	map.put(old, map.get(old) - 1);
            }
            
            if(map.containsKey(now)) {
            	map.put(now, map.get(now) + 1); //이미 있으면 개수추가
            } else {
            	map.put(now, 1);
            	findByBefore(now);
            }
            sb.append(res).append('\n');
        }     
        System.out.println(sb);
    }
    
    public static void findByBefore(long v) {
    	int finquotient = (int) (v / div);
    	long finremainder = v % div;
    	int quotient = finquotient;
        long remainder = finremainder;
        
        Long before = yes[quotient].lower(remainder);
        while(before == null && 0 < quotient) {
    		quotient--;
    		if(!yes[quotient].isEmpty())
    			before = yes[quotient].first();
    	}
        
        //이전점이 없거나 커버불가능한 점이면 지금점을 시작점으로 만듬
        if(before == null || before + L < v) {
        	res++;
        	yes[finquotient].add(finremainder);
        	YesToNot(v , v + L);
    	} else{
    		not[finquotient].add(finremainder);
    	}
    }
    
//  범위안에 Not이 있으면 Yes으로 옮김
    public static void NotToYes(long start , long end) {
    	int quotient = (int) (start / div);
        long remainder = start % div;
    	
    	Long next = not[quotient].higher(remainder);
    	while(next == null && quotient + 1 < div) {
    		quotient++;
    		if(!not[quotient].isEmpty())
    			next = not[quotient].first();
    	}
    	
    	if(next == null || end < quotient * div + next) {
    		return;
    	} else{
    		not[quotient].remove(next);
    		yes[quotient].add(next);
    		start = quotient * div + next;
    		res++;
    		YesToNot(start , start + L);
    	}
    }
    
//    범위안에 Yes가 있으면 Not으로 옮김
    public static void YesToNot(long start , long end) {
    	int quotient = (int) (start / div);
        long remainder = start % div;
        Long next = yes[quotient].higher(remainder);
    	while(next == null && quotient + 1 < div) {
    		quotient++;
    		if(!yes[quotient].isEmpty())
    			next = yes[quotient].first();
    	}
    	
    	if(next == null || end < quotient * div + next) {
    		return;
    	} else{
    		yes[quotient].remove(next);
    		not[quotient].add(next);
    		start = quotient * div + next;
    		res--;
    		NotToYes(end , start + L);
    	}
	}

	public static int readInt() throws Exception {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        boolean flag = (c == '-');
        if (flag) c = System.in.read();
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);

        if (flag) return -val;
        return val;
    }

    public static long readLong() throws Exception {
        long val = 0;
        long c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        boolean flag = (c == '-');
        if (flag) c = System.in.read();
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);

        if (flag) return -val;
        return val;
    }
}