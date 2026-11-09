import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] requestCounts = new int[k];
        
        PriorityQueue<int[]> busyHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        TreeSet<Integer> idleServers = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            idleServers.add(i);
        }
        
        for (int i = 0; i < arrival.length; i++) {
            int startTime = arrival[i];
            
            while (!busyHeap.isEmpty() && busyHeap.peek()[0] <= startTime) {
                idleServers.add(busyHeap.poll()[1]);
            }
            
            if (idleServers.isEmpty()) {
                continue;
            }
            
            Integer chosenServer = idleServers.ceiling(i % k);
            if (chosenServer == null) {
                chosenServer = idleServers.first(); // Wrap around
            }
            
            idleServers.remove(chosenServer);
            requestCounts[chosenServer]++;
            busyHeap.offer(new int[]{startTime + load[i], chosenServer});
        }
        
        int maxRequests = 0;
        for (int count : requestCounts) {
            maxRequests = Math.max(maxRequests, count);
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCounts[i] == maxRequests) {
                result.add(i);
            }
        }
        return result;
    }
}
