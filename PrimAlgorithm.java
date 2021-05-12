//Importing the necassary packages and files for the program.
package maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author khalidahmed
 */

//Creating a publicly accessible class named PrimAlgorithm with private instance variables.
public class PrimAlgorithm {
    //Creating a new instance constructor from the Random package impoted aboove.
    private static final Random random = new Random();
    //Declaring 2 List interfaces of type string to create a new array list and assignning them to a set.
    private static final List<String> minimumSpanningTree = new ArrayList<>();
    private static final List<String> nodesInTree = new ArrayList<>();
    //Declaring a pair instance variables of type 2D integer arrays.
    private static int[][] nodeMatrix;
    private static int[][] weightsMatrix;
    //Declaring and initializing 5 integer type instance fields.
    private static int weightsMatrixSize = 0;
    private static int primHeight = 0;
    private static int primWidth = 0;
    private static int mazeHeight = 0;
    private static int mazeWidth = 0;
    //Declaring an instance variable of type 2D string arrays.
    private static String[][] maze;

    //Declaring a method which has a return type of a 2D String array and takes in two int type parameters. 
    public static String[][] generateMazeWithPrimAlgorithm(int height, int width) {
        //Using the .clear() method to remove but not delete the elements from our sets below.
        minimumSpanningTree.clear();
        nodesInTree.clear();
        //Using relational & comparison operators to evaluate the expression that is to be assignned to out instance fields which were initialized earlier.
        primHeight = height % 2 == 0 ? (height / 2) - 1 : height / 2;
        primWidth = width % 2 == 0 ? (width / 2) - 1 : width / 2;

        //Assigning another instance variable the pproduct of the instance variables evaluated above,
        weightsMatrixSize = primHeight * primWidth;
        //Assigning mazeHeight and mazeWidth with the parameters that were passed into our function earlier.
        mazeHeight = height;
        mazeWidth = width;
        //Creating a new maze object that takes the parameters as index values of type String.
        maze = new String[height][width];
        //A similar object is created with the only difference being the index values are of type int.
        nodeMatrix = new int[height][width];
        //Another similar object is created but takes in the same instance variable twice.
        weightsMatrix = new int[weightsMatrixSize][weightsMatrixSize];
        //Declaring an int type variable named node and initializing it to 0.
        int node = 0;
        
        //Using a nested for loop to keep track of our height and width adjacency and at the same time looping for the count by incrementing the node.
        for (int i = 0; i < primHeight; i++) {
            for (int j = 0; j < primWidth; j++) {
                nodeMatrix[i][j] = node;
                node++;
            }
        }
        
        //Again another nested for loop is used to maintain the size off our matrix. 
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
    
    //Creating a function that has a void return value and willl return our maze.
    public static void createMaze() {

        //Filling in the maze with walls using a for loop and assigning the maze object with walls that will allow it to display two space characters.
        for (int i = 0; i < mazeHeight; i++) {
            for (int j = 0; j < mazeWidth; j++) {
                maze[i][j] = "\u2588\u2588";
            }
        }

        //Using a for loop to determine the edges usig a minimum spanning tree.
        for (String edge : minimumSpanningTree) {
            //Creating a 1D array of type strinng called coordinates in order to lleave space between the edges using the split method and white space as an argument.
            String[] coordinates = edge.split(" ");
            //All of these assignment statements are written when an intteger has to be passed in order to parse and determine coordinates.
            int nodeH1 = Integer.parseInt(coordinates[0]) * 2 + 1;
            int nodeW1 = Integer.parseInt(coordinates[1]) * 2 + 1;
            int nodeH2 = Integer.parseInt(coordinates[2]) * 2 + 1;
            int nodeW2 = Integer.parseInt(coordinates[3]) * 2 + 1;
            //A new int type node is declared and is evaluated by the two other nodes then divided by 2 and assigned the value.
            int nodeH3 = (nodeH1 + nodeH2) / 2;
            int nodeW3 = (nodeW1 + nodeW2) / 2;

            //Creating a path between two connected nodes.
            maze[nodeH1][nodeW1] = "  ";
            maze[nodeH2][nodeW2] = "  ";
            maze[nodeH3][nodeW3] = "  ";

        }
    }
    
    //Creating a variable that generates either horizontal or vertical entrance and exit for the maze.
    public static void createEntranceExitInMaze() {
        //Setting two booolean values that wll be used later on for allowing users to exit horizontally or not.
        boolean isHorizontalEntranceExit = random.nextBoolean();
        boolean isGenerated = false;
        
        //The if statement generates a horizontal exit fand entrance for the maze.
        if (isHorizontalEntranceExit) {
            //The condition is then evaluated to be true or fase.
            while (!isGenerated) {
                //Once the truth or falsehood of the values are evaluated then i and j can be asigned their respective values.
                int i = random.nextInt(mazeHeight);
                int j = 0;
                //If an empty cell is determined to exist then maze and is generated will be assigned their respective values.
                if ("  ".equals(maze[i][j + 1])) {
                    maze[i][j] = "  ";
                    isGenerated = true;
                }
            }
            //A false boolean value is being generated.
            isGenerated = false;

            //Since the condition in the while loop is true the statements in the body will be evaluated.
            while (!isGenerated) {
                int i = random.nextInt(mazeHeight);
                int j = mazeWidth - 1;
                
                //The conditions below will be checked with aan ampersand should the width of the maze be greater than 0.
                if (mazeWidth % 2 > 0  && "  ".equals(maze[i][j - 1])) {
                    maze[i][j] = "  ";
                    isGenerated = true;
                }
                //This condition will be checked and evaluated if the width is equal to 0.
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
            //A false boolean value is being generated.
            isGenerated = false;
            
            //The same logic that occured above for the width will occue below here for the maze height.
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
