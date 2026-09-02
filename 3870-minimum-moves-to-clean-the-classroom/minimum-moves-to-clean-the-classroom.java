class Solution {
    static class State{
        int r, c, mask,energy;
        State(int r, int c, int mask, int energy){
            this.r = r;
            this.c = c;
            this.mask = mask;
            this.energy = energy;
        }
    }
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int startR = -1, startC = -1;
        List<int[]> litterList = new ArrayList<>();

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                char ch = classroom[i].charAt(j);
                if(ch =='S'){
                    startR = i;
                    startC = j;
                }else if(ch == 'L'){
                    litterList.add(new int[]{i, j});
                }
            }
        }
        int k = litterList.size();
        int targetMask = (1 << k) - 1;

        int[][][] maxEnergy = new int[m][n][1 << k];
        for(int[][] matrix : maxEnergy){
            for(int[] row : matrix){
                Arrays.fill(row, -1);
            }
        }

        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(startR, startC, 0, energy));
        maxEnergy[startR][startC][0] = energy;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        int moves = 0;

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                State curr = queue.poll();

                if(curr.mask == targetMask){
                    return moves;
                }
                if(curr.energy == 0){
                    continue;
                }
                for(int d = 0; d < 4; d++){
                    int nr = curr.r + dr[d];
                    int nc = curr.c + dc[d];

                    if(nr < 0 || nr >= m || nc < 0 || nc >= n || classroom[nr].charAt(nc) == 'X'){
                        continue;
                    }
                    char nextCell = classroom[nr].charAt(nc);
                    int nextMask = curr.mask;
                    int nextEnergy = curr.energy - 1;

                    if(nextCell == 'R'){
                        nextEnergy = energy;
                    }

                    if(nextCell == 'L'){
                        for(int idx= 0; idx < k; idx++){
                            if(litterList.get(idx)[0] == nr && litterList.get(idx)[1] == nc){
                                nextMask |= (1 << idx);
                                break;

                            }
                        }
                    }
                    if(nextEnergy > maxEnergy[nr][nc][nextMask]){
                        maxEnergy[nr][nc][nextMask] = nextEnergy;
                        queue.offer(new State(nr, nc,nextMask,nextEnergy));
                    }
                }

            
            }
            moves++;
        }
        return -1;
        

    }
}