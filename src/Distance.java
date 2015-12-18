import java.util.Vector;

public class Distance {
	int n = 0;
	
	final static int m = 30000;
	int data[][];
	
	boolean visit[];
	int dis[];
	int prev[];
	
	int s, e;
	int stack[];
	
	Vector<Integer> stackV;
	
	public void init(int data1[][]) {
		data = data1;
		n = data.length;
		
		dis = new int[n];
		visit = new boolean[n];
		prev = new int[n];
		stack = new int[n];
		stackV = new Vector<Integer>();
	}
	
	public int theLeastDistance() {
		System.out.println("Least Distance : " + dis[e-1]);
		return dis[e-1];
	}
	
	public void start(int start, int end) {
		CharToNum ctn = new CharToNum();
		start = ctn.charToNumber1(start);
		end = ctn.charToNumber2(end);
		
		s = start;
		e = end;
		
		int k = 0;
		int min = 0;
		
		for(int i = 0; i < n; i++) {
			dis[i] = m;
			prev[i] = 0;
			visit[i] = false;
		}
		
		dis[s-1] = 0;
		
		for(int i = 0; i < n; i++) {
			min = m;
			for(int j = 0; j < n; j++) {
				if(visit[j] == false && dis[j] < min) {
					k = j;
					min = dis[j];
				}
			}
			
			visit[k] = true;
			
			if(min == m) break;
			
			for(int j = 0; j < n; j++) {
				if(dis[k] + data[k][j] < dis[j]) {
					dis[j] = dis[k] + data[k][j];
					prev[j] = k;
				}
			}
		}
		inverseFind();
	}
	
	private void inverseFind() {
		int tmp = 0;
		int top = -1;
		tmp = e -1;
		while(true) {
			stack[++top] = tmp + 1;
			if(tmp == s - 1) break;
			tmp = prev[tmp];
		}
		
		stackV.removeAllElements();
		for(int i = top; i > -1; i--) {
			System.out.println("Stack : " + stack[i]);
			stackV.add(stack[i]);
			if(i!=0) System.out.println("->");
		}
		System.out.println("\n");
	}
	
	public Vector<Integer> getStack() {
		System.out.println("StackV : " + stackV);
		return stackV;
	}
	
	public static void main(String[] args) {
		int m = 30000;
		int[][] data = new int[][] {
			{0, 5, m, 2, 4},
			{5, 0, 3, 6, 8},
			{m, 3, 0, m, 8},
			{2, 6, m, 0, 6},
			{4, 8, 8, 6, m}
		};
		Distance d = new Distance();
		d.init(data);
		
		String str = "CA";
		char initialCha = 0;
		char lastCha = 0;
		int totalD = 0;
		for(int i = 0; i < str.length(); i++) {
			try {
				initialCha = str.charAt(i);
				if(i < str.length() - 1) {
					lastCha = str.charAt(i + 1);
				} else {
					lastCha = str.charAt(i);
				}
				d.start(initialCha, lastCha);
				
				int nodeD = d.theLeastDistance();
				totalD += nodeD;
				System.out.println("Total Distance : " + totalD);
				d.getStack();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
