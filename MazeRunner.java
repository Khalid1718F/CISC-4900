package mazerunner;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Jonathan
 */
public class MazeRunner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {createGUI();});
    }
    private static void createGUI(){
        JFrame frame = new JFrame("Maze Runner");
        Maze maze = new Maze();
        maze.setMaze(Main.makeTheMaze(10, 10), 10, 10);
        
        frame.add(maze);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
    
    private static class Maze extends JPanel{
        int[][] maze;
        int width, height;
        //wall size
        final int WALL = 30;

        void setMaze(int[][] maze, int width, int height){
            this.maze = maze;
            this.width = width;
            this.height = height;
        }
        @Override
        public void paint(Graphics g){
            super.paint(g);
            g.setColor(Color.BLACK);
            for(int i = 0; i < height; ++i){
                for(int j = 0; j < width; ++j){
                    if(maze[i][j] == 1)
                        g.fillRect(j * WALL, i * WALL, WALL, WALL);
                }
            }
        }
        @Override
        public Dimension getPreferredSize(){
            return new Dimension(width * WALL, height * WALL);
        }
    }
}

















