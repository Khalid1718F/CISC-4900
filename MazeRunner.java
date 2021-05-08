package maze;

import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 *
 * @author Jonathan
 */
public class MazeRunner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){}
        SwingUtilities.invokeLater(() -> {createGUI();});
    }
    private static void createGUI(){
        JFrame frame = new JFrame("Maze Runner");
        JPanel panel = new JPanel();
        MazePanel mp = new MazePanel();
        panel.add(mp);
        
        mp.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                switch(e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        mp.moveCircle(mp.getCircleX(), mp.getCircleY()-5);
                        break;
                    case KeyEvent.VK_DOWN:
                        mp.moveCircle(mp.getCircleX(), mp.getCircleY()+5);
                        break;
                    case KeyEvent.VK_LEFT:
                        mp.moveCircle(mp.getCircleX()-5, mp.getCircleY());
                        break;
                    case KeyEvent.VK_RIGHT:
                        mp.moveCircle(mp.getCircleX()+5, mp.getCircleY());
                        break;
                }
            }
        });
        
        Maze maze = new Maze(5, 5);
        
        JPanel menu = new JPanel();
        JButton generateMazeButton = new JButton("Generate a new maze");
        JButton loadMazeButton = new JButton("Load a maze");
        JButton saveMazeButton = new JButton("Save maze");
        
        generateMazeButton.addActionListener((ActionEvent ae) -> {
            int width;
            int height;
            JTextField widthField = new JTextField(10);
            JLabel label1 = new JLabel("Width: ");
            JTextField heightField = new JTextField(10);
            JLabel label2 = new JLabel("Height: ");
            Object[] o = {label1, widthField, label2, heightField};
            JOptionPane.showMessageDialog(
                    frame,
                    o,
                    "Enter the size of a maze",
                    JOptionPane.QUESTION_MESSAGE);
            try{
                width = Integer.parseInt(widthField.getText());
                height = Integer.parseInt(heightField.getText());
            }catch(Exception e){
                JOptionPane.showMessageDialog(
                        frame,
                        "Width and height should be specified as integers.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(width < 5 || height < 5){
                JOptionPane.showMessageDialog(
                        frame,
                        "Width and height should be greater than 4.",
                        "Wrong size",
                        JOptionPane.ERROR_MESSAGE);
            }else{
                maze = new Maze(height, width);
                saveMazeButton.setVisible(true);
                mp.setMaze(maze.getMaze());
                mp.repaint();
                mp.revalidate();
                mp.requestFocus();
                frame.pack();
            }
        });
        
        loadMazeButton.addActionListener((ActionEvent ae) -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setFileFilter(new FileNameExtensionFilter(".maze", ".maze"));
            if(fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION)
                if(maze.loadFromFile(fileChooser.getSelectedFile())){
                    saveMazeButton.setVisible(true);
                    mp.setMaze(maze.getMaze());
                    mp.repaint();
                    mp.revalidate();
                    mp.requestFocus();
                    frame.pack();
                }
        });
        
        saveMazeButton.setVisible(false);
        saveMazeButton.addActionListener((ActionEvent ae) -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setFileFilter(new FileNameExtensionFilter(".maze", ".maze"));
            if(fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION)
                if(!maze.saveToFile(fileChooser.getSelectedFile())){
                    JOptionPane.showMessageDialog(
                            frame,
                            "Something went wrong.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
        });
        
        menu.add(generateMazeButton);
        menu.add(loadMazeButton);
        menu.add(saveMazeButton);
        
        frame.add(panel);
        frame.add(menu, BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.pack();
        frame.setVisible(true);
    }
    
    private static class MazePanel extends JPanel{
        int[][] maze;
        int width, height;
        //wall size
        final int WALL = 30;
        
        //circle location
        int x, y;
        
        //maze exit
        int exitX, exitY;
        
        boolean won;

        void setMaze(int[][] maze){
            this.maze = maze;
            height = maze.length;
            width = maze[0].length;
            won = false;
            boolean b = true;
            for(int i = 0; i < height; ++i){
                for(int j = 0; j < width; ++j){
                    if(maze[i][j] == 0){
                        if(i == 0 || i == height-1 || j == 0 || j == width-1){
                            if(b){
                                x = j * WALL;
                                y = i * WALL;
                                b = false;
                            }else{
                                exitX = j * WALL;
                                exitY = i * WALL;
                                break;
                            }
                        }
                    }
                }
            }
        }
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            if(won){
                g.setColor(Color.BLACK);
                g.drawString("you won", (getWidth() / 2) - 25, getHeight() / 2);
                return;
            }
            g.setColor(Color.BLACK);
            for(int i = 0; i < height; ++i){
                for(int j = 0; j < width; ++j){
                    if(maze[i][j] == 1)
                        g.fillRect(j * WALL, i * WALL, WALL, WALL);
                }
            }
            if(maze != null){
                g.setColor(Color.GREEN);
                g.fillOval(x, y, 30, 30);
                if(x == exitX && y == exitY){
                    won = true;
                    repaint();
                }
            }
        }
        @Override
        public Dimension getPreferredSize(){
            return new Dimension(width * WALL, height * WALL);
        }
        void moveCircle(int x, int y){
            if(maze == null || won ||
                    x < 0 || y < 0 || x >= width*WALL || y >= height*WALL)
                return;
            int offsetX = 0;
            int offsetY = 0;
            if(x % WALL > 0 && x > this.x)
                offsetX = 1;
            if(y % WALL > 0 && y > this.y)
                offsetY = 1;
            if(maze[(y / WALL) + offsetY][(x / WALL) + offsetX] == 0){
                this.y = y;
                this.x = x;
                repaint();
            }
        }
        int getCircleX(){
            return x;
        }
        int getCircleY(){
            return y;
        }
    }
}


