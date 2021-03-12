package mazerunner;

import java.io.*;

/**
 *
 * @author Jonathan
 */
interface Maze {
    
    /*
    Generates and returns a 2D int array representing a maze
    @param width width of the maze
    @param height height of the maze
    @returns 2D int array representing a maze. Cells with walls are marked with '1'.
            Cells without walls are marked with '0'.
    */
    int[][] generateMaze(int width, int height);
    
    /*
    Loads a maze from file and returns as a 2D int array.
    @param file the file that may contain a maze
    @returns 2D int array representing a maze. Cells with walls are marked with '1'.
            Cells without walls are marked with '0'.
    @throws Exception if file doesn't contain a maze. The message must say
                      "Incorrect file format."
    @throws FileNotFoundException if the file doesn't exist
    */
    int[][] loadMaze(File file) throws Exception, FileNotFoundException;
    
    /*
    Saves the current maze to file.
    @param file the file to which the current maze will be saved
    @returns true if successful, otherwise returns false
    @throws Exception if file name already exists. The message must say
                    "File name already exists."
    */
    boolean saveMaze(File file) throws Exception;
    
    /*
    Gets a 2D int array with '1' in cells where escape path lies, other
    cells must be '0'.
    @returns a 2D int array with '1' in cells where escape path lies, other
            cells must contain '0'.
    */
    int[][] getEscapePath();
    
}









