<h1>Maze Runner</h1>
<br/>
<br/>
<br/>

Khalid Ahmed, Jonathan Hanin
<br/>
Contact emails: Khalid.Ahmed9119@gmail.com, cisc4900@mail.com
<br/>
Sections VC1C & VC1B
<br/>
<br/>
Supervisor:
<br/>
Contact email:
<br/>
<br/>

<H2>Abstract:<H2/>

Mazes are absolutely amazing! Imagine the thrill of running, avoiding enemies and obstacles,
and that gratifyingly triumphant feeling of reaching the exit if you can! Besides being a loads of fun,
maze games are actually valuable learning tools for children and adults alike.
Maze solving games has numerous benefits which are included but not limited to:
1) Improvement of patience and persistence in players.
2) The enhancement of cognotive and deductive reasoning abilities.
3) Excellent in the improvement of hand-eye coordination.
4) Development of memory improvement and strategic planning.

***Project Details:***

The project will be broken up into several stages each with the purpose accomplishing differnt tasks.
Here is a break down of the steps in sequence:
<br/>

Stage #1: At this stage, it does not matter what maze you display:
      
        a. The maze will be implemented as a two-dimensional array of integers.
        b. If the value of an element is 1, it is a wall. If the value is 0, it is a passage.
        c. The dimensions of the maze must be 10 X 10.
        d. There should be walls around the maze, except for two cells: entrance and exit.
        e. Any empty cell should be accessible from the entrance or exit of the maze.
        f. It is impossible to move diagonally, only vertically or horizontally.
        g. There must be a path between the entrance and exit and they are interchangeable.
        h. After starting the program it should simply output a maze then stop.
        j. The program may always output the same prepared maze or one of a set of prepared mazes.
<br/>

Stage #2: In this second stage the algorithm for the game will be developed:
          
          a. The maze should not contain 3x3 blocks consisting of walls only. The entire maze area should be filled with pathways.
          b. The algorithm for building a maze, is based on the construction of a minimal spanning tree.
          c. This is done using Prim's Algorithm. [Here is a video for more on how the Algorithm works](https://www.youtube.com/watch?v=KurrmC-sd6k)
          d. The user must be able to specify the size of a the maze.
          e. After a user enters the size, the program must print a maze and then stop.
          f. After starting, the program prompts user to enter the size of the maze. 
          g. Then output a generated maze, and then stop running the program.
          h. The first values entered will be the height and the second will be the width.
          
<br/>

Stage #3: In this third stage the players should be able to have 5 menu options:

          a. After a maze is generated or loaded from a file, it becomes the current maze that can be saved or displayed.
          b. If there is no current maze (generated or loaded), a user should not see the options save and display the maze.
          c. If a user chooses an option that requires a file, he must enter a path to the file.
          d. The program should output the maze to the user only in two scenarios:
               - After generating a maze.
               - After choosing an option to display a maze.
          e. The program should be able to successfully handle the following test cases:
               - If an incorrect option was chosen, the program must print a message like `Incorrect option. Please try again`
               - If a file to load a maze does not exist, the program should not stop, it must print a message like `The file ... does not exist`
               - If a file has an invalid format for a maze, the program shouldn’t stop,it must print a message like `Cannot load the maze. It has an invalid format`

          d. After starting, your program must print a menu listing only appropriate options. When a user has chosen an option, the program must perform the corresponding action.
          
<br/>

     
          










