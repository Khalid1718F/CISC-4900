//Importing necassary packages and libraries.
package maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
*@author Khalid
*/

//Creating a Prim algorithm class.
public class PrimAlgorithm {
    private static final Random random = new Random(); 
    private static final List<String> minimumSpanningTree = new ArrayList<>();
    private static final List<String> nodesInTree = new ArrayList<>();
    private static int[][] nodeMatrix;
    private static int[][] weightsMatrix;
    private static int weightsMatrixSize = 0;
    private static int primHeight = 0;
    private static int primWidth = 0;
    private static int mazeHeight = 0;
    private static int mazeWidth = 0;
    private static String[][] maze;


    public static String[][] generateMazeWithPrimAlgorithm(int height, int width) {
        minimumSpanningTree.clear();
        nodesInTree.clear();

        primHeight = height % 2 == 0 ? (height / 2) - 1 : height / 2;
        primWidth = width % 2 == 0 ? (width / 2) - 1 : width / 2;


        weightsMatrixSize = primHeight * primWidth;
        mazeHeight = height;
        mazeWidth = width;
        maze = new String[height][width];

        nodeMatrix = new int[height][width];
        weightsMatrix = new int[weightsMatrixSize][weightsMatrixSize];
        int node = 0;

        for (int i = 0; i < primHeight; i++) {
            for (int j = 0; j < primWidth; j++) {
                nodeMatrix[i][j] = node;
                node++;
            }
        }

        for (int i = 0; i < weightsMatrixSize; i++) {
            for (int j = 0; j < weightsMatrixSize; j++) {
                weightsMatrix[i][j] = 0;
            }
        }

        for (int i = 0; i < primHeight; i++) {
            for (int j = 0; j < primWidth; j++) {
                if (i < primHeight - 1 && j < primWidth - 1) {
                    weightsMatrix[nodeMatrix[i][j]][nodeMatrix[i][j + 1]] = random.nextInt(9) + 1;
                    weightsMatrix[nodeMatrix[i][j + 1]][nodeMatrix[i][j]] = weightsMatrix[nodeMatrix[i][j]][nodeMatrix[i][j + 1]];

                    weightsMatrix[nodeMatrix[i][j]][nodeMatrix[i + 1][j]] = random.nextInt(9) + 1;
                    weightsMatrix[nodeMatrix[i + 1][j]][nodeMatrix[i][j]] = weightsMatrix[nodeMatrix[i][j]][nodeMatrix[i + 1][j]];

                } else if (j == primWidth - 1 && i != primHeight - 1) {
                    weightsMatrix[nodeMatrix[i][j]][nodeMatrix[i + 1][j]] = random.nextInt(9) + 1;
                    weightsMatrix[nodeMatrix[i + 1][j]][nodeMatrix[i][j]] = weightsMatrix[nodeMatrix[i][j]][nodeMatrix[i + 1][j]];
                } else if (j != primWidth - 1 && i == primHeight - 1) {
                    weightsMatrix[nodeMatrix[i][j]][nodeMatrix[i][j + 1]] = random.nextInt(9) + 1;
                    weightsMatrix[nodeMatrix[i][j + 1]][nodeMatrix[i][j]] = weightsMatrix[nodeMatrix[i][j]][nodeMatrix[i][j + 1]];
                }
            }
        }
        

        int rootNodeHeight = random.nextInt(primHeight);
        int rootNodeWidth = random.nextInt(primWidth);
        nodesInTree.add(rootNodeHeight + " " + rootNodeWidth);

//        for debug only - prints the root node where findMinimumSpanningTree algorithm starts from
//        System.out.println(rootNodeHeight + " " + rootNodeWidth);

        findMinimumSpanningTree(nodesInTree);
        createMaze();
        createEntranceExitInMaze();

        return maze;
    }

    public static List<String> findMinimumSpanningTree(List<String> nodesInTree) {
        if (nodesInTree.size() == weightsMatrixSize) {
            return nodesInTree;
        } else {
            int minWeight = 100;
            int nextNodeHeight = 0;
            int nextNodeWidth = 0;
            String pairNode = "";

            for (String nodes : nodesInTree) {
                String[] coordinates = nodes.split(" ");
                int coordinateHeight = Integer.parseInt(coordinates[0]);
                int coordinateWidth = Integer.parseInt(coordinates[1]);

                if (coordinateHeight == 0) {
                    if (coordinateWidth < primWidth - 1) {
                        if (weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight][coordinateWidth + 1]] < minWeight && !nodesInTree.contains(coordinateHeight + " " + (coordinateWidth + 1))) {
                            minWeight = weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight][coordinateWidth + 1]];
                            nextNodeHeight = coordinateHeight;
                            nextNodeWidth = coordinateWidth + 1;
                            pairNode = nodes;
                        }
                    }

                    if (coordinateWidth > 0) {
                        if (weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight][coordinateWidth - 1]] < minWeight && !nodesInTree.contains(coordinateHeight + " " + (coordinateWidth - 1))) {
                            minWeight = weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight][coordinateWidth - 1]];
                            nextNodeHeight = coordinateHeight;
                            nextNodeWidth = coordinateWidth - 1;
                            pairNode = nodes;
                        }
                    }

                    if (weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight + 1][coordinateWidth]] < minWeight && !nodesInTree.contains((coordinateHeight + 1) + " " + coordinateWidth)) {
                        minWeight = weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight + 1][coordinateWidth]];
                        nextNodeHeight = coordinateHeight + 1;
                        nextNodeWidth = coordinateWidth;
                        pairNode = nodes;
                    }
                }

                if (coordinateHeight == primHeight - 1) {
                    if (coordinateWidth > 0) {
                        if (weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight][coordinateWidth - 1]] < minWeight && !nodesInTree.contains(coordinateHeight + " " + (coordinateWidth - 1))) {
                            minWeight = weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight][coordinateWidth - 1]];
                            nextNodeHeight = coordinateHeight;
                            nextNodeWidth = coordinateWidth - 1;
                            pairNode = nodes;
                        }
                    }

                    if (coordinateWidth < primWidth - 1) {
                        if (weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight][coordinateWidth + 1]] < minWeight && !nodesInTree.contains(coordinateHeight + " " + (coordinateWidth + 1))) {
                            minWeight = weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight][coordinateWidth + 1]];
                            nextNodeHeight = coordinateHeight;
                            nextNodeWidth = coordinateWidth + 1;
                            pairNode = nodes;
                        }
                    }

                    if (weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight - 1][coordinateWidth]] < minWeight && !nodesInTree.contains((coordinateHeight - 1) + " " + coordinateWidth)) {
                        minWeight = weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight - 1][coordinateWidth]];
                        nextNodeHeight = coordinateHeight - 1;
                        nextNodeWidth = coordinateWidth;
                        pairNode = nodes;
                    }
                }


                if (coordinateWidth == 0) {
                    if (weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight][coordinateWidth + 1]] < minWeight && !nodesInTree.contains(coordinateHeight + " " + (coordinateWidth + 1))) {
                        minWeight = weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight][coordinateWidth + 1]];
                        nextNodeHeight = coordinateHeight;
                        nextNodeWidth = coordinateWidth + 1;
                        pairNode = nodes;
                    }

                    if (coordinateHeight > 0) {
                        if (weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight - 1][coordinateWidth]] < minWeight && !nodesInTree.contains((coordinateHeight - 1) + " " + coordinateWidth)) {
                            minWeight = weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight - 1][coordinateWidth]];
                            nextNodeHeight = coordinateHeight - 1;
                            nextNodeWidth = coordinateWidth;
                            pairNode = nodes;
                        }
                    }

                    if (coordinateHeight < primHeight - 1) {
                        if (weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight + 1][coordinateWidth]] < minWeight && !nodesInTree.contains((coordinateHeight + 1) + " " + coordinateWidth)) {
                            minWeight = weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight + 1][coordinateWidth]];
                            nextNodeHeight = coordinateHeight + 1;
                            nextNodeWidth = coordinateWidth;
                            pairNode = nodes;
                        }
                    }
                }


                if (coordinateWidth == primWidth - 1) {
                    if (weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight][coordinateWidth - 1]] < minWeight && !nodesInTree.contains(coordinateHeight + " " + (coordinateWidth - 1))) {
                        minWeight = weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight][coordinateWidth - 1]];
                        nextNodeHeight = coordinateHeight;
                        nextNodeWidth = coordinateWidth - 1;
                        pairNode = nodes;
                    }

                    if (coordinateHeight > 0) {
                        if (weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight - 1][coordinateWidth]] < minWeight && !nodesInTree.contains((coordinateHeight - 1) + " " + coordinateWidth)) {
                            minWeight = weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight - 1][coordinateWidth]];
                            nextNodeHeight = coordinateHeight - 1;
                            nextNodeWidth = coordinateWidth;
                            pairNode = nodes;
                        }
                    }

                    if (coordinateHeight < primHeight - 1) {
                        if (weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight + 1][coordinateWidth]] < minWeight && !nodesInTree.contains((coordinateHeight + 1) + " " + coordinateWidth)) {
                            minWeight = weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight + 1][coordinateWidth]];
                            nextNodeHeight = coordinateHeight + 1;
                            nextNodeWidth = coordinateWidth;
                            pairNode = nodes;
                        }
                    }
                }


                if (coordinateHeight > 0 && coordinateHeight < primHeight - 1 && coordinateWidth > 0 && coordinateWidth < primWidth - 1) {
                    if (weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight][coordinateWidth - 1]] < minWeight && !nodesInTree.contains(coordinateHeight + " " + (coordinateWidth - 1))) {
                        minWeight = weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight][coordinateWidth - 1]];
                        nextNodeHeight = coordinateHeight;
                        nextNodeWidth = coordinateWidth - 1;
                        pairNode = nodes;
                    }
                    if (weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight][coordinateWidth + 1]] < minWeight && !nodesInTree.contains(coordinateHeight + " " + (coordinateWidth + 1))) {
                        minWeight = weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight][coordinateWidth + 1]];
                        nextNodeHeight = coordinateHeight;
                        nextNodeWidth = coordinateWidth + 1;
                        pairNode = nodes;
                    }
                    if (weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight - 1][coordinateWidth]] < minWeight && !nodesInTree.contains((coordinateHeight - 1) + " " + coordinateWidth)) {
                        minWeight = weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight - 1][coordinateWidth]];
                        nextNodeHeight = coordinateHeight - 1;
                        nextNodeWidth = coordinateWidth;
                        pairNode = nodes;
                    }
                    if (weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight + 1][coordinateWidth]] < minWeight && !nodesInTree.contains((coordinateHeight + 1) + " " + coordinateWidth)) {
                        minWeight = weightsMatrix[nodeMatrix[coordinateHeight][coordinateWidth]][nodeMatrix[coordinateHeight + 1][coordinateWidth]];
                        nextNodeHeight = coordinateHeight + 1;
                        nextNodeWidth = coordinateWidth;
                        pairNode = nodes;
                    }
                }
            }

            nodesInTree.add(nextNodeHeight + " " + nextNodeWidth);
            minimumSpanningTree.add(pairNode + " " + nextNodeHeight + " " + nextNodeWidth);

//          for debug only - prints coordinates of minimum spanning tree
//          System.out.println(minimumSpanningTree);
        }
        return findMinimumSpanningTree(nodesInTree);
    }

    public static void createMaze() {

        //fill the maze with walls
        for (int i = 0; i < mazeHeight; i++) {
            for (int j = 0; j < mazeWidth; j++) {
                maze[i][j] = "\u2588\u2588";
            }
        }

        //
        for (String edge : minimumSpanningTree) {
            String[] coordinates = edge.split(" ");
            int nodeH1 = Integer.parseInt(coordinates[0]) * 2 + 1;
            int nodeW1 = Integer.parseInt(coordinates[1]) * 2 + 1;
            int nodeH2 = Integer.parseInt(coordinates[2]) * 2 + 1;
            int nodeW2 = Integer.parseInt(coordinates[3]) * 2 + 1;

            int nodeH3 = (nodeH1 + nodeH2) / 2;
            int nodeW3 = (nodeW1 + nodeW2) / 2;

            //create paths between two connected nodes
            maze[nodeH1][nodeW1] = "  ";
            maze[nodeH2][nodeW2] = "  ";
            maze[nodeH3][nodeW3] = "  ";

        }
    }

    //generates either horizontal or vertical entrance and exit of the maze
    public static void createEntranceExitInMaze() {
        boolean isHorizontalEntranceExit = random.nextBoolean();
        boolean isGenerated = false;

        if (isHorizontalEntranceExit) {
            while (!isGenerated) {
                int i = random.nextInt(mazeHeight);
                int j = 0;

                if ("  ".equals(maze[i][j + 1])) {
                    maze[i][j] = "  ";
                    isGenerated = true;
                }
            }

            isGenerated = false;

            while (!isGenerated) {
                int i = random.nextInt(mazeHeight);
                int j = mazeWidth - 1;

                if (mazeWidth % 2 > 0 && "  ".equals(maze[i][j - 1])) {
                    maze[i][j] = "  ";
                    isGenerated = true;
                }

                if (mazeWidth % 2 == 0 && "  ".equals(maze[i][j - 2])) {
                    maze[i][j] = "  ";
                    maze[i][j - 1] = " ";
                    isGenerated = true;
                }
            }
        } else {
            while (!isGenerated) {
                int i = 0;
                int j = random.nextInt(mazeWidth);

                if ("  ".equals(maze[i + 1][j])) {
                    maze[i][j] = "  ";
                    isGenerated = true;
                }
            }

            isGenerated = false;

            while (!isGenerated) {
                int i = mazeHeight - 1;
                int j = random.nextInt(mazeWidth);

                if (mazeHeight % 2 > 0 && "  ".equals(maze[i - 1][j])) {
                    maze[i][j] = "  ";
                    isGenerated = true;
                }

                if (mazeHeight % 2 == 0 && "  ".equals(maze[i - 2][j])) {
                    maze[i][j] = "  ";
                    maze[i - 1][j] = "  ";
                    isGenerated = true;
                }
            }
        }
    }
}
