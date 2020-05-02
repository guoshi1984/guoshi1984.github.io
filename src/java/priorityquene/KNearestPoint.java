import java.util.*;

/* use a priority queue to store the K nearest points, since we look for the K minimum distance, we use max heap. The reason to do max heap is to allow us
 * to pup out the maximum element.
 * 
 */

public class KNearestPoint {
	public static List<List<Integer>> topK(List<List<Integer>> input, int n, int k){
        	LinkedList<List<Integer>> ans = new LinkedList<>();
        	PriorityQueue<List<Integer>> maxHeap = new PriorityQueue<List<Integer>>(k, new Comparator<List<Integer>>() {
            		public int compare(List<Integer> a, List<Integer> b) {
				// decsending order
            			return getDistance2(b) - getDistance2(a);
			}
        	});

        	for (List<Integer> c: input) {
            		maxHeap.offer(c);
            		if (!maxHeap.isEmpty() && maxHeap.size() > k) {
                		maxHeap.poll();
            		}
        	}
        	while (!maxHeap.isEmpty()) {
            		ans.addFirst(new ArrayList<Integer>(maxHeap.poll()));
        	}
        	return ans;
    	}

	public static int getDistance2(List<Integer> a) {
		return a.get(0)*a.get(0) + a.get(1)*a.get(1);
	}

    	public static void main (String[] args){
        	List<List<Integer>> input = new ArrayList<>();
        	input.add(Arrays.asList(-1,0));
        	input.add(Arrays.asList(1,1));
        	input.add(Arrays.asList(1,2));
        	input.add(Arrays.asList(1,3));
        	input.add(Arrays.asList(1,4));
        	input.add(Arrays.asList(2,5));
        	int n = 6;
        	int k = 2;
        	System.out.println(topK(input,n,k));
    	}

}
