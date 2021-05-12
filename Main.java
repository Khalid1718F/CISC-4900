// Importing the necessary packages and libraries
package maze;
import java.io.File;
import java.util.Scanner;

/**
 * @author khalidahmed
 */
// Creating a Main class.
public class Main {
    // A static variable to hold the state if maze is created or not
    // Currently the maze is not created. So it is initialised to false
    private static boolean isMazeCreated = false;

    // Main function
    public static void main(String[] args) {
        // Creating an object of scanner class
        Scanner scanner = new Scanner(System.in);
        // Creating variable to check if size entered is correct or not
        boolean isSizeCorrect = false;
        // Variables to store height and width of maze
        int height = 0;
        int width = 0;
        // A maze variable to store the current maze
        Maze maze = null;
        // Creating an infinite loop
        // This loop will be exited if user enters 0
        while (true) {
            // Showing the menu of the screen using showMenu function
            showMenu();
            // Creating a switch based on input from the user
            switch (scanner.nextLine()) {
                // If user enters 1
                case "1":
                    // Continue until user enters the correct size
                    while (!isSizeCorrect) {
                        System.out.println("Please, enter the size of a maze");
                        try {
                            // Splitting the input from the user into an array
                            String[] mazeSize = scanner.nextLine().split(" ");
                            // If user enters 2 numbers, then first is height and second is width
                            // If user enters 1 number, then height and width are same
                            if (mazeSize.length == 2) {
                                // Converting the strings into integers
                                height = Integer.parseInt(mazeSize[0]);
                                width = Integer.parseInt(mazeSize[1]);
                            } else {
                                // Converting the string into integers
                                height = Integer.parseInt(mazeSize[0]);
                                width = height;
                            }
                            // Height and width should be greater than 4
                            if (height > 4 && width > 4) {
                                isSizeCorrect = true;
                            }
                        } catch (NumberFormatException e) {
                            // If user enters something other than numbers as input
                            System.out.println("Wrong size format. Input height and width of a maze. Both sizes should be bigger than 4.");
                        }
                    }
                    isSizeCorrect = false;
                    // Creating an object of Maze class
                    maze = new Maze(height, width);
                    // Marking that the maze is created now
                    isMazeCreated = true;
                    // Printing the maze
                    maze.printMaze();

                    break;
                // If user enters 2
                case "2":
                    // Variable to store if the maze is loaded successfully or not
                    boolean isMazeLoadedFromFile = false;
                    // Creating a maze of fixed size (5, 5)
                    maze = new Maze(5, 5);
                    // Continue until the maze is loaded successfully
                    while (!isMazeLoadedFromFile) {
                        // Ask for the path of file from user
                        System.out.println("Enter path to the file and its name:");
                        // Taking file input
                        File file = new File(scanner.nextLine());
                        // Check if the maze is loaded successfully from the mentioned file
                        isMazeLoadedFromFile = maze.loadFromFile(file);
                    }
                    // Mark that the maze is successfully created
                    isMazeCreated = true;
                    break;
                // If user enters 3
                case "3":
                    // Check if maze is already created and it is not null
                    // Else it cannot be saved in the file
                    if (isMazeCreated && maze != null) {
                        // Variable to store if the maze is saved successfully or not
                        boolean isMazeSavedToFile = false;
                        // Continue until the maze is saved successfully
                        while (!isMazeSavedToFile) {
                            // Ask for the path of file from user
                            System.out.println("Enter path to the file and its name:");
                            // Taking file input
                            File file = new File(scanner.nextLine());
                            // Check if the maze is saved successfully in the mentioned file
                            isMazeSavedToFile = maze.saveToFile(file);
                        }
                    } else {
                        // Print that the maze doesn't exist and the user has chosen something wrong
                        System.out.println("Incorrect option. Please try again");
                    }
                    break;
                // If user enters 4
                case "4":
                    // Check if maze is already created and it is not null
                    // Else it cannot be printed
                    if (isMazeCreated && maze != null) {
                        // Printing the maze
                        maze.printMaze();
                    } else {
                        // Print that the maze doesn't exist and the user has chosen something wrong
                        System.out.println("Incorrect option. Please try again");
                    }
                    break;
                // If user enters 0
                case "0":
                    // The program ends here
                    System.out.println("Bye!");
                    System.exit(0);
                    // If user enters the wrong choice
                default:
                    System.out.println("Incorrect option. Please try again");
                    break;
            }
        }
    }
    // ShowMenu function
    public static void showMenu() {
        // Check if maze is created
        if (!isMazeCreated) {
            // if the maze is not created
            // Then we can have only two options
            // Either create one or load one
            System.out.print("\n=== Menu ===\n" +
                    "1. Generate a new maze.\n" +
                    "2. Load a maze.\n" +
                    "0. Exit.\n");
        } else {
            // if the maze is created
            // We can use all the functions
            // Printing menu accordingly
            System.out.print("\n=== Menu ===\n" +
                    "1. Generate a new maze.\n" +
                    "2. Load a maze.\n" +
                    "3. Save the maze.\n" +
                    "4. Display the maze.\n" +
                    "0. Exit.\n");
        }
    }
}
