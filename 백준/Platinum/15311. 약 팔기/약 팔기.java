public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(2000).append('\n');
		for (int i = 0; i < 2000; i++) {
			sb.append(1);
			if(i < 1000)
				sb.append("000");
			sb.append(' ');
		}
		System.out.println(sb);
	}
}