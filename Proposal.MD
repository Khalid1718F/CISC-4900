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
Supervisor: Allan James Lapid
<br/>
Contact email: allanjames.lapid@brooklyn.cuny.edu



Abstract:

Mazes are absolutely amazing! Imagine the thrill of running, avoiding enemies and obstacles,
and that gratifyingly triumphant feeling of reaching the exit if you can! Besides being a loads of fun,
maze games are actually valuable learning tools for children and adults alike.
Maze solving games has numerous benefits which are included but not limited to:
1) Improvement of patience and persistence in players.
2) The enhancement of cognotive and deductive reasoning abilities.
3) Excellent in the improvement of hand-eye coordination.
4) Development of memory improvement and strategic planning.

***Project Details:***

The project will be broken up into several stages each with the purpose accomplishing different tasks.
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

Stage #4: In this fourth stage the players should be able to find a way out of the maze.

          a. Player should be able to find a way to escape the maze.
          b. This option should be available only if a current maze exists.
          c. Data about the escape path should not be saved to the files and not display an escape path when user chooses to do so.
          d. The escape symbol should be marked with the '/' symbol.
          e. After starting, the program must print a menu listing only appropriate options.
          f. When a user has chosen an option, the program must perform the corresponding action.
          g. Most importantly, the program shouldn’t stop unless the user selects the exit option.
          
<br/>
     
Stage #5: In this fifth stage the enemy is trying to catch the player.

          a. The enemy should find its way in the maze to catch the player.
          b. The player will be given 3-5 tries to complete the maze without being caught by the enemy.
          c. If the player is unsuccessful 3-5 times in a row, "Game Over" is displayed and the player will be given the option to retry this maze or generate a new one.
          d. At any time the player can choose the option to generate a new maze.
          
<br/>
     
Technical Overview:

The project will be implemented in Java and a graphical user interface will be included.
In Stage #2 we will be implementing Prims algorithm, along with Kruskal's algorithm.
This stage will use appropriate graph algorithms, to enable the program to generate a random maze of a given size.
In Stage #4 there will also be data structures involved to teach the program to trace a way out of the trickiest mazes.
There will also be several OOP concepts that will be used during the development of this game.

Background:

We both have a decent understanding of OOP.
Since our core programming classes were Java based we regard this project as a good fit for us.
There are certain concepts that were not covered in our academic studies.
This is why a project such as this seems like a good opportunity to help us sharpen our skills.
     
**Estimated timeline:**<br/>
Define the interfaces between the game representation and GUI, weeks 6-7<br/>
Implement the maze and its graphical representation, weeks 7-8<br/>
Implement the algorithm for building the maze and complete stage 3, week 9<br/>
Implement the enemy and its graphical representation, weeks 10-11<br/>
Test game with users, weeks 12-13<br/>
Prepare final report and submit, weeks 14-15

