package priorityqueue;

import java.io.*;
import java.util.*;

public class B_1655 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		PriorityQueue<Integer> maxP = new PriorityQueue<>((a,b)->Integer.compare(b, a));
		PriorityQueue<Integer> minP = new PriorityQueue<>();
		
		
		int N = Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++) {
			int num = Integer.parseInt(br.readLine());
			
			if(maxP.size()==minP.size()) maxP.offer(num);
			else minP.offer(num);
			
			if(minP.isEmpty()) {
				sb.append(maxP.peek()).append("\n");
			}
			else {
				if(maxP.peek()>minP.peek()) {
					int max_value = maxP.poll();
					int min_value = minP.poll();
					maxP.offer(min_value);
					minP.offer(max_value);
				}
				
				sb.append(maxP.peek()).append("\n");
			}
		}
		System.out.println(sb);
	}

}
