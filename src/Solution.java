import java.util.HashMap;

public class Solution {

    public static int getResult(String field, String race) {

        // maps with cost of moving for each type of cell for all creatures
        HashMap<String, Integer> human = new HashMap<>();
        human.put("S", 5);
        human.put("W", 2);
        human.put("T", 3);
        human.put("P", 1);
        HashMap<String, Integer> swamper = new HashMap<>();
        swamper.put("S", 2);
        swamper.put("W", 2);
        swamper.put("T", 5);
        swamper.put("P", 2);
        HashMap<String, Integer> woodman = new HashMap<>();
        woodman.put("S", 3);
        woodman.put("W", 3);
        woodman.put("T", 2);
        woodman.put("P", 2);

        // map with all races
        HashMap<String, HashMap<String, Integer>> races = new HashMap<>();
        races.put("Human", human);
        races.put("Swamper", swamper);
        races.put("Woodman", woodman);

        final int ROWS = 4;
        final int COLUMNS = 4;
        // map with costs of cells for our race
        int[][] costMap = new int[ROWS][COLUMNS];
        int count = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                char currentCell = field.charAt(count++); // type of current cell
                costMap[i][j] = races.get(race).get(String.valueOf(currentCell)); // get cost of movement for race in cell
            }
        }

        // matrix which shows costs between cells
        int[][] weightMatix = new int[ROWS * COLUMNS][ROWS * COLUMNS];
        int cellNum = 0; // check paths for cells from 0 to ROWS*COLUMNS-1
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (i > 0) { // check cell at top
                    weightMatix[cellNum][cellNum - COLUMNS] = costMap[i-1][j];
                }
                if (i < ROWS - 1) { // check cell at bottom
                    weightMatix[cellNum][cellNum + COLUMNS] = costMap[i+1][j];
                }
                if (j > 0) { // check cell at left
                    weightMatix[cellNum][cellNum - 1] = costMap[i][j-1];
                }
                if (j < COLUMNS - 1) { // check cell at right
                    weightMatix[cellNum][cellNum + 1] = costMap[i][j+1];
                }
                cellNum++;
            }
        }

        // find min distance with dijkstra algorithm
        boolean[] isVisited = new boolean[ROWS*COLUMNS]; // is nth cell was visited?
        int[] minDistTo = new int[ROWS*COLUMNS]; // min dist from start to each cell
        for (int i = 0; i < ROWS*COLUMNS; i++) {
            minDistTo[i] = Integer.MAX_VALUE;
        }
        minDistTo[0] = 0;

        int currentCell = 0;
        while (!isVisited[ROWS*COLUMNS-1]) {
            for (int i = 0; i < minDistTo.length; i++) { // update all min dists from start to cell
                if (weightMatix[currentCell][i] != 0) { // have path between current cell and nth cell
                    if (minDistTo[i] > minDistTo[currentCell] + weightMatix[currentCell][i]) { // if start -> i longer than start -> currentCell -> i
                        minDistTo[i] = minDistTo[currentCell] + weightMatix[currentCell][i];
                    }
                }
            }
            isVisited[currentCell] = true;

            // change currentCell to nearest cell
            int dist2Nearest = Integer.MAX_VALUE;
            for (int i = 0; i < ROWS*COLUMNS; i++) { // find nearest cell from start and not visited yet
                if (!isVisited[i] && (minDistTo[i] < dist2Nearest)) {
                    dist2Nearest = minDistTo[i];
                    currentCell = i;
                }
            }
        }

        return minDistTo[ROWS*COLUMNS-1];
    }

}
