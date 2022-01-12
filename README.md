Raduta Lavinia-Maria 323CA
# Object Oriented Programming Course
## Project - Santa Claus is coming to ACS Students

January 2021
### Description of packages:
1. **commands**: contains all the commands that do all the tasks throughout
the simulation.
2. **databases**: contains the databases for children, gifts and a database that
manage children, gifts and changes and is used to create the other two
databases.
3. **entities**: contains the entities used in the program and 2 factories to
create children and gifts from the input information classes.
4. **fileio**: contains the classes used to store the initial information from
the input JSONs and the classes for writing the output in the result JSONs.
5. **sorting**: contains the strategies used to sort the children in the database in order to receive gifts.
6. **score_strategies**: contains the strategies used for computing the nicescore for
all the children categories and a factory to create the strategy needed.

###The simulation flow
- The **Simulation** class is the one that coordinates all the flow of the
program, as it applies the changes of every year.
- The first thing that needs to be done is to **populate the databases**.
- Now we have the information for the first year of the simulation, and we
can start applying the commands and strategies:
    - **delete the young adults**, because they will not receive gifts;
    - calculate the **nice scores** for all the remaining children including the
  bonus received;
    - calculate the **budget for every child** using the
      formula specific to the type of elf each child has;
    - decide which gifts every child receive and give them.
    - if a child has a **yellow elf** and has not received a gift yet, the cheapest
  gift from his favourite category will be assigned. 
- Then the **Output** object is created.
- Output will contain a list of children databases, every element being **a
version of the children database** from the correspondent year (every year a
  **deep copy** of the children database is added at the list contained in the
output class).
- Then for each round of the simulation:
  - the **new budget** is set;
  - the **new gifts and new children** are added to the correct database;
  - the list of children is **sorted** according to the strategy mentioned in the
  input file;
  - the children database in **updated** in order to add it changed to the output at the end
  of the round.
  - the children list is finally **sorted by the children's id** in order to be added
  - to the output.
- The update of the children database consists in:
    - **increasing the age** of all the children and checking if any of them
    became young adult (if so, remove them from the database);
    - for every child mentioned in the update list, **changing the attributes**
    (add a new nicescore, add the new preferences, change the type of elf);
    - then the **applying of strategies and commands** is repeated as in the
    initial stage.
- After all the rounds, the output is written to the out JSON.

###Design Patterns used.
1. **Singleton Pattern**: Used for databases and strategies factories.
2. **Factory**: Used to create a concrete strategy for sort and average score computing.
3. **Command**: Used to implement all the step of the update of the database
4. **Strategy**: Used to encapsulate the algorithm of sorting and calculating the scores.
