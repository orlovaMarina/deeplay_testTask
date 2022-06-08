import java.util.*;

public class Solution {

    public static int getResult(String str, String race){
        return shortestPath(getMatrix(str, race));
    }

    /**
     *Custom class for representing
     * row-index, column-index &
     * distance of each cell
     */
   private static class Cell
    {
        private int x;
        private int y;
        private int cost;

        public Cell(int x, int y, int cost)
        {   this.x = x;
            this.y = y;
            this.cost = cost;
        }


        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getCost() {
            return cost;
        }
    }

    /**
     *Utility method to check whether current
     * cell is inside matrix or not
     */
    private static boolean isInsideMatrix(int i, int j)
    {
        return (i >= 0 && i < 4 &&
                j >= 0 && j < 4);
    }

    private static int[][] getMatrix(String str, String race){
        int[][] matrix = new int[4][4];

        char[] chars = str.toCharArray();

        char[] letters = new char[]{'S', 'W', 'T', 'P'};

        Map<Character, Integer> map = new HashMap<>();
        switch (race) {
            case "Human" -> {
                map.put(letters[0], 5);
                map.put(letters[1], 2);
                map.put(letters[2], 3);
                map.put(letters[3], 1);
            }
            case "Swamper" -> {
                map.put(letters[0], 2);
                map.put(letters[1], 2);
                map.put(letters[2], 5);
                map.put(letters[3], 2);
            }
            case "Woodman" -> {
                map.put(letters[0], 3);
                map.put(letters[1], 3);
                map.put(letters[2], 2);
                map.put(letters[3], 2);
            }
        }


        if(str.length()==16) {
            for(int i = 0, k = 0; i < matrix.length; i++){
                for(int j = 0; j < matrix[i].length; j++){
                    matrix[i][j] = map.get(chars[k++]);
                }
            }
        }
        return matrix;
    }
    /**
     *  Method to return shortest path from
     * top-corner to bottom-corner in 2D grid
     */

   private static int shortestPath(int[][] grid){
       /**
         * all possible directions to move in from a cell
        */
       final int[] dx = { -1, 0, 1, 0 };
       final int[] dy = { 0, 1, 0, -1 };

        int[][] cost = new int[4][4];
        for (int[] ints : cost) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }

        grid[0][0] = 0;
        cost[0][0] = grid[0][0];

        PriorityQueue<Cell> pq = new PriorityQueue<Cell>(
                4 * 4, new Comparator<Cell>() {
            /**
             * Custom comparator for inserting cells into Priority Queue -
             * we poll cells with min cost first
             */
            @Override
            public int compare(Cell a, Cell b) {
                {
                    if (a.getCost() < b.getCost())
                    {
                        return -1;
                    }
                    else if (a.getCost() > b.getCost())
                    {
                        return 1;
                    }
                    else {
                        return 0;
                    }
                }
            }
        });

        pq.add(new Cell(0, 0, cost[0][0]));
        while (!pq.isEmpty())
        {
            Cell curr = pq.poll();
            for(int i = 0; i < 4; i++)
            {
                int rows = curr.getX() + dx[i];
                int cols = curr.getY() + dy[i];

                if (isInsideMatrix(rows, cols))
                {
                    if (cost[rows][cols] >
                            cost[curr.getX()][curr.getY()] +
                                    grid[rows][cols])
                    {

                        // If Cell is already been reached once,
                        // remove it from priority queue
                        if (cost[rows][cols] != Integer.MAX_VALUE)
                        {
                            Cell adj = new Cell(rows, cols,
                                    cost[rows][cols]);

                            pq.remove(adj);
                        }

                        // Insert cell with updated distance
                        cost[rows][cols] = cost[curr.getX()][curr.getY()] +
                                grid[rows][cols];

                        pq.add(new Cell(rows, cols,
                                cost[rows][cols]));
                    }
                }
            }
        }
        return cost[cost.length - 1][cost.length - 1];
    }



    public static void main(String[] args) {
        System.out.println(getResult("SPWTPSPWTSPWWSWP", "Woodman"));

    }
}

