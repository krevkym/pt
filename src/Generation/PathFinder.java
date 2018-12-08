package Generation;

import java.util.ArrayList;
import java.util.Collections;

public class PathFinder {

    private int[][] matrixOfPredecessors;
    private int[][] distanceMatrix;
    private int cost;

    private ArrayList<Integer> path = new ArrayList<>();

    public PathFinder(int[][] distanceMatrix) {

        this.distanceMatrix = distanceMatrix;
        this.matrixOfPredecessors = createMatrixOfPredecessors(distanceMatrix);
        cost = 0;
    }

    private static int[][] createInitialMatrix(int[][] d) {
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

    public int[][] createMatrixOfPredecessors(int[][] d) {
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
        return p;
    }

    public void findPath(int start, int end) {
            if(path.size() == 0) {
                path.add(end);
            }
            if(start == end) {
                Collections.reverse(path);
            } else if(matrixOfPredecessors[start][end] != -1) {
                //cost += distanceMatrix[start][end];
                path.add(matrixOfPredecessors[start][end]);
                findPath(start, matrixOfPredecessors[start][end]);
            }

            /*if(matrixOfPredecessors[start][end] == -1) {
                System.out.print(start + " " + end + " ");
                path.add(end);
            } else {
                findPath(start, matrixOfPredecessors[start][end]);
                findPath(matrixOfPredecessors[start][end], end);
            }*/


        /*if (start == end) {
            path.add(start);
        } else if(matrixOfPredecessors[start][end] == -1) {
        System.out.println("Cesta neexistuje");
        } else {
            findPath(start, matrixOfPredecessors[start][end]);
            path.add(end);
        }*/
    }


    public int findCostInDistMat(ArrayList<Integer> path) {
        int cost2 = 0;
        for(int i = 0; i < path.size()-1; i++) {
            cost2 += distanceMatrix[path.get(i)][path.get(i+1)];
        }
        return cost2;
    }

    public int getCost() {
        return cost;
    }

    public ArrayList<Integer> getPath() {
        return path;
    }

    public void clearPath() {
        this.path.clear();
    }
    public void clearCost() {
        this.cost = 0;
    }
}
