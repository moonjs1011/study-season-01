import java.io.*;
import java.util.*;

public class Main {
	static int N, E;
	static List<int[]>[] dist;
	static int n1,n2;
	
	
	// O((E+V)logV)
	static int[] dijkstra(int s) {
		int[] arr = new int[N+1];
		Arrays.fill(arr, Integer.MAX_VALUE>>2);
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)-> (Integer.compare(o1[2], o2[2])));
		
		arr[s] = 0;
		// basic // O(E)
		for(int[] c : dist[s]) {
			pq.add(c);
			arr[c[1]] = c[2];
//			System.out.println(Arrays.toString(c));
		}
		// O((E+V)logV)
		while(!pq.isEmpty()) {
			int[] c = pq.poll(); //O(logV) 
			if(c[2]> arr[c[1]]) continue;
			for(int[] nc : dist[c[1]]) { //O(V+E) // 총 E개, 그대신 하나당 최대 V개 있을수 있어서? 잘 모르겠네
				if(arr[nc[1]]> c[2]+ nc[2]) {
					arr[nc[1]] = c[2]+ nc[2];
					pq.add(new int[] {c[0],nc[1],arr[nc[1]]});
				}
			}
		}
		
		
		
		return arr;
	}
	
	public static void main(String[] args) throws IOException {
		
			try {
				System.setIn(new FileInputStream(new File("./input.txt")));				
			}
			catch(Exception e) {
				// 아닌게벼
			}
			BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = null;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			dist = new List[N+1];
			for(int i = 0; i<N+1;i++) {
				dist[i] = new ArrayList<int[]>();
			}
			
			//O(E)
			for(int i = 0; i< E; i++) {
				int a,b,c;
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				dist[a].add(new int[] {a,b,c});
				dist[b].add(new int[] {b,a,c});
			}
			st = new StringTokenizer(br.readLine());
			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());
			
			// 1 -> n1,n2
			// n1->n2 or n2 -> n1 // 무향이라 얘도 최단 아닌가? 
			// n1,n2 -> e;
			//O((E+V)logV)
			int[] sarr = dijkstra(1);
			int[] rarr = dijkstra(n1);
			int[] earr = dijkstra(N);
			
			int result = Integer.MAX_VALUE >>3;
			result = Math.min(result, Math.min(sarr[n1] + rarr[n2]+earr[n2], sarr[n2] + rarr[n2]+earr[n1]));
			if(result >= (Integer.MAX_VALUE >>3)) {
				System.out.println(-1);				
			}
			else {
				System.out.println(result);
			}
			br.close();
	}

}
