import java.io.*;
import java.util.*;

public class BOJ_G2_1655_가운데를말해요 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /* Priority Queue */
        // offer(E e), poll() => O(log N)
        // peek(), size(), isEmpty() => O(1)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());

        /* O(N * log N) */
        for (int n=0; n<N; n++) {
            int num = Integer.parseInt(br.readLine());

            /* O(log N) */
            if (maxHeap.size() == minHeap.size()) {
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }

            /* O(log N) */
            // minHeap is not empty => maxHeap is not empty
            if (!minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                int a = maxHeap.poll();
                int b = minHeap.poll();
                maxHeap.offer(b); minHeap.offer(a);
            }

            System.out.println(maxHeap.peek());
			br.close();
        }




    }
}