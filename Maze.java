// Importing the necessary packages and libraries
package maze;
import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author khalidahmed
 */


// Creating class called Maze.
public class Maze {
    private String[][] maze; //Creating a 2-D array of type String to represent the maze.
    private int height; //Creating an Integer variable to store height of the maze.
    private int width; //Creating an Integer variable to store the width of the maze.l

    public Maze(int height, int width) //Creating a constructor with the height & width as parameters.
    {
        //Including this in in the maze package in order to generate a new maze using the PrimAlgorithm.
        this.maze = PrimAlgorithm.generateMazeWithPrimAlgorithm(height, width);
        this.height = height; //setting height
        this.width = width; //setting width
    }

    //Creating a function to build the maze.
    public void printMaze()
    {
        for (int i = 0; i < height; i++) //Looping to traverse the height.
        {
            for (int j = 0; j < width; j++) //Looping to traverse the width.
            {
                System.out.print(maze[i][j]); //Printing element in the maze at index {i,j}
            }
            System.out.println(); //Printing a new line.
        }
    }

    //Creating a function to save data to the file where file it passed as an argument.
    public boolean saveToFile(File file)
    {
        //Making FileWriter object to write data into file
        try (FileWriter writer = new FileWriter(file)) {
            //Writing the height and width to the file where the data is stored.
            writer.write(height + " " + width + "\n");

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    writer.write(maze[i][j]); //Writing element at index {i,j} into the file.
                }
                writer.write("\n"); //Adding a newline.
            }

            //Catching an IO exception to check if the file is correctly opened or not.
        } catch (IOException e) {
            //Displays text to the user if the file cannot be saved and shows the faulty file name.
            System.out.println("Cannot save the maze. It has an invalid format " + file);
            return false; //Returns a false value.
        }
        return true; //If an exception does not occur then a value of true will be returned.
    }

    //Creating a function which is used to read the data from a file where file is passed as an argument.
    public boolean loadFromFile(File file)
    {
        try (Scanner scanner = new Scanner(file)) { //Making scanner object to read from file.
            String[] mazeSize = scanner.nextLine().split(" "); //Reading the first line of words into mazeSize array.
            int tempHeight = Integer.parseInt(mazeSize[0]); //The string at 0-th index will be height, and will convert the string into an integer value.
            int tempWeight = Integer.parseInt(mazeSize[1]); //The string at 1-st index will be width, and will convert the string into an integer value.

            String[][] tempMaze = new String[tempHeight][tempWeight]; //Creating a new String type array of size height * width.

            for (int i = 0; i < tempHeight; i++) {
                String line = scanner.nextLine(); //Reading a line from the file.
                int k = 0;
                for (int j = 0; j < tempWeight; j++) {
                    tempMaze[i][j] = line.charAt(k) + "" + line.charAt(k + 1); //Storing the value at index k and k+1 of the line into our maze.
                    k += 2; //Incrementing k by 2.
                }
            }

            height = tempHeight; //Assigning height to tempHeight.
            width = tempWeight; //Assigning width to tempWeight.
            maze = tempMaze; //Assigning maze to tempMaze.

        } catch (FileNotFoundException ex) { //Checking for an exception whether or not the file exists.
            //Displaying error message to the user to demonstrate that the file exists.
            System.out.println("The file " + file + " does not exist");
            return false; //If file does not exist a false value is returned.
            //Checking if index is out of bounds and if no such exception exists.
        } catch (IndexOutOfBoundsException | NoSuchElementException | NumberFormatException exc) {
            //Displaying error message to user that maze does not exist and returns a false value.
            System.out.println("Cannot load the maze. It has an invalid format");
            return false;
        }
        return true; //if no exception occurs then true value is returned.
    }
    
    //Creating a publically accessible function that returns a 2D integer array and takes no parameters.
    public int[][] getMaze(){
        //Creating a new instance of a 2D array object that takes in height annd width as it's indices.
        int[][] m = new int[height][width];
        //Creating a nested for loop where height and width are evaluated and iterated over. 
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                //If the height and width are at maze sub i & maze sub j and it also equals a block
                //then the value of m at i and j are equal to 1 otherwise theyre equal to 0 and m is returned.
                if(maze[i][j].equals("\u2588\u2588")) {
                    m[i][j] = 1;
                }else{
                    m[i][j] = 0;
                }
            }
        }
        return m;
    }
}
