package Generation;

import java.util.ArrayList;
import java.util.Collections;

public class PathFinder {

    private int[][] matrix;
    private int[][] matrix2;
    private int cost = 0;
    private ArrayList<Integer> path = new ArrayList<>();

    public PathFinder(int[][] matrix) {
        this.matrix2 = matrix;
        this.matrix = matrix;
    }

    private int[][] createInitialMatrix(int[][] d) {
        int[][] p = new int[d.length][d.length];
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d.length; j++) {
                if (d[i][j] != 0 && d[i][j] != Integer.MAX_VALUE) {
                    p[i][j] = i;
                } else {
                    p[i][j] = -1;
                }
            }
        }
        return p;
    }



    public void createMatrixOfPredecessors(int[][] d) {
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d.length; j++) {
                if(d[i][j] == -1) {
                    d[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        int[][] p = createInitialMatrix(d);
        for (int k = 0; k < d.length; k++) {
            for (int i = 0; i < d.length; i++) {
                for (int j = 0; j < d.length; j++) {
                    if (d[i][k] == Integer.MAX_VALUE || d[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }

                    if (d[i][j] > d[i][k] + d[k][j]) {
                        d[i][j] = d[i][k] + d[k][j];
                        p[i][j] = p[k][j];
                    }

                }
            }
        }
        this.matrix = p;
    }

    public ArrayList<Integer> findPath(int start, int end) {
        if(path.size() == 0) {
            path.add(end);
        }
        if(start == end) {
            return path;
        }
        else if(matrix[start][end] == 0) {
            return null;
        }
        else {
            cost += matrix2[start][end];
            path.add(matrix[start][end]);
            findPath(start, matrix[start][end]);
            Collections.reverse(path);
            return path;
        }
    }

    public int getCost() {
        return cost;
    }
}
