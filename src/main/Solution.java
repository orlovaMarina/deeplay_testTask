import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;

public class Solution {
    private static final int ROW = 4;
    private static final int COL = 4;

    public static int getResult(String str, String race) {
        return shortestPath(getMatrix(str, race));
    }

    /**
     * Custom class for representing
     * row-index, column-index &
     * distance of each cell
     */
    private static class Cell {
        private int x;
        private int y;
        private int cost;

        public Cell(int x, int y, int cost) {
            this.x = x;
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
     * Utility method to check whether current
     * cell is inside matrix or not
     */
    private static boolean isInsideMatrix(int i, int j) {
        return (i >= 0 && i < ROW &&
                j >= 0 && j < COL);
    }

    private static int[][] getMatrix(String str, String race) {
        int[][] matrix = new int[ROW][COL];
        char[] chars = str.toCharArray();
//вот здесь лучше не хардкодить путь к файлу.
//Мне кажется, лучше создать директорию src/main/resources и туда положить файлик с примером. То есть, добавить прям в проект.
//Тогда удобнее будет посмотреть формат файла и вносить туда какие-то изменения, не трогая сам код.
        //создать директорию src/main/resources и туда положить файлик с примером.
        Map<String, Map<String, Integer>> allRacesData = getDataFromFile();

        try{
            Map<String, Integer> raceData = allRacesData.get(race);

            for (int i = 0, k = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    matrix[i][j] = raceData.get(String.valueOf(chars[k++]));
                }
            }
        }catch(NullPointerException exc){
            System.out.println("Race " + race + " is not found in the database." );
        }

        return matrix;
    }

    /**
     * Method to read data from a file.
     * The file should be with .csv extension and contain data in the following pattern: "Race, Obstacle, Cost" in every line
     */
    private static Map<String, Map<String, Integer>> getDataFromFile() {
        Map<String, Map<String, Integer>> outer = new HashMap<>();
        String line = "";
        String splitBy = ", ";
        try {
            URL resource = Solution.class.getResource("inputData.csv");
            BufferedReader br = new BufferedReader(new FileReader(Paths.get(resource.toURI()).toFile()));

            while ((line = br.readLine()) != null){
                String[] lineArray = line.split(splitBy);

                String race = lineArray[0];
                String obstacle = lineArray[1];
                Integer cost = Integer.parseInt(lineArray[2]);

                if(outer.containsKey(race)){
                    outer.get(race).put(obstacle,cost);
                }
                else {
                    Map<String, Integer> inner = new HashMap<>();
                    inner.put(obstacle, cost);
                    outer.put(race, inner);
                }
            }
        }
        catch(IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return outer;
    }

    /**
     * Method to return shortest path from
     * top-corner to bottom-corner in 2D grid
     */
    private static int shortestPath(int[][] grid) {
        /**
         * All possible directions to move in from a cell
         */
        final int[] dx = {-1, 0, 1, 0};
        final int[] dy = {0, 1, 0, -1};

        int[][] cost = new int[ROW][COL];
        for (int[] ints : cost) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }

        grid[0][0] = 0;
        cost[0][0] = grid[0][0];

        PriorityQueue<Cell> pq = new PriorityQueue<Cell>(
                ROW * COL, new Comparator<Cell>() {
            /**
             * Custom comparator for inserting cells into Priority Queue -
             * we poll cells with min cost first
             */
            @Override
            public int compare(Cell a, Cell b) {
                {
                    if (a.getCost() < b.getCost()) {
                        return -1;
                    } else if (a.getCost() > b.getCost()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        });

        pq.add(new Cell(0, 0, cost[0][0]));
        while (!pq.isEmpty()) {
            Cell curr = pq.poll();
            for (int i = 0; i < 4; i++) {
                int rows = curr.getX() + dx[i];
                int cols = curr.getY() + dy[i];

                if (isInsideMatrix(rows, cols)) {
                    if (cost[rows][cols] >
                            cost[curr.getX()][curr.getY()] +
                                    grid[rows][cols]) {

                        // If Cell is already been reached once,
                        // remove it from priority queue
                        if (cost[rows][cols] != Integer.MAX_VALUE) {
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
        System.out.println(getResult("TTPSWPTSWPTSWPST", "Human"));

    }
}
