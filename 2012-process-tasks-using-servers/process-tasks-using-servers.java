class Solution {
    class Server {
        int weight;
        int index;
        long freeTime;

        Server(int weight, int index, long freeTime) {
            this.weight = weight;
            this.index = index;
            this.freeTime = freeTime;
        }
    }

    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        PriorityQueue<Server> idle = new PriorityQueue<>((a, b) -> {
            if (a.weight != b.weight) {
                return Integer.compare(a.weight, b.weight);
            }
            return Integer.compare(a.index, b.index);
        });

        PriorityQueue<Server> busy = new PriorityQueue<>((a, b) -> {
            if (a.freeTime != b.freeTime) {
                return Long.compare(a.freeTime, b.freeTime);
            }
            if (a.weight != b.weight) {
                return Integer.compare(a.weight, b.weight);
            }
            return Integer.compare(a.index, b.index);
        });

        for (int i = 0; i < n; i++) {
            idle.offer(new Server(servers[i], i, 0));
        }

        long currentTime = 0;

        for (int j = 0; j < m; j++) {
            currentTime = Math.max(currentTime, j);

            while (!busy.isEmpty() && busy.peek().freeTime <= currentTime) {
                idle.offer(busy.poll());
            }

            if (idle.isEmpty()) {
                currentTime = busy.peek().freeTime;
                while (!busy.isEmpty() && busy.peek().freeTime <= currentTime) {
                    idle.offer(busy.poll());
                }
            }

            Server server = idle.poll();
            ans[j] = server.index;
            server.freeTime = currentTime + tasks[j];
            busy.offer(server);
        }

        return ans;
    }
}
