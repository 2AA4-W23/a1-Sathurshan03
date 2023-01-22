# A1 - Piraten Karpen

  * Author: Sathurshan Arulmohan
  * Email: arulmohs@mcmaster.ca

## Build and Execution

  * To clean your working directory:
    * `mvn clean`
  * To compile the project:
    * `mvn compile`
  * To run the project in development mode:
    * `mvn -q exec:java -Dexec.args='random combo'` (here, `-q` tells maven to be _quiet_) ('random combo' can be replaced with any combination of `random` and `combo` greater than 2 in length)
  * To run the project in development mode with trace logs:
    * `mvn -q exec:java -DtraceMode=True -Dexec.args='random combo'` 
  * To package the project as a turn-key artefact:
    * `mvn package`
  * To run the packaged delivery:
    * `java -jar target/piraten-karpen-jar-with-dependencies.jar random combo` ("random combo" can be replaced with any combination of `random` and `combo` greater than 2 in length)
  * To run the packaged delivery with Trace Mode:
    * `java -jar -DtraceMode=True target/piraten-karpen-jar-with-dependencies.jar random combo` 

Remark: **We are assuming here you are using a _real_ shell (e.g., anything but PowerShell on Windows)**

## Feature Backlog

 * Status: 
   * Pending (P), Started (S), Blocked (B), Done (D)
 * Definition of Done (DoD):
   * A feature is marked as "Done" whenever the implementation successfully satisfies the minimum requirement of that feature. The implemented feature must not alter the status and outcome of previously implemented features. 

### Backlog 

| Id  | Feature  | Status  |  Started  | Delivered |
|:-:  |---       | :-:     | :-:       | :-:       |
| F01 | Roll a dice |  D | 01/12/23 | 01/12/23 |
| F02 | Roll eight dices  |  D | 01/12/23  | 01/12/23 |
| F03 | Run 42 games in simulation  |  D  |  01/12/23 | 01/12/23 |
| F04 | end of game with three cranes | D | 01/12/23 | 01/12/23 |
| F05 | Player keeping random number of dice at their turn | D | 01/12/23 | 01/12/23 | 
| F06 | Score points: 3 or more of a kind | D | 01/12/23 | 01/12/23 |
| F07 | Score points: gold coins and diamonds | D | 01/12/23 | 01/12/23 | 
| F08 | Output percent wins at end of simulation | D | 01/12/23 | 01/12/23 |
| F09 | Have two players in simulation | D | 01/12/23 | 01/12/23 |
| F10 | Play a game until there is a winner | D | 01/12/23 | 01/12/23 |
| F11 | Output the exact dice that gets re-rolled | D | 01/14/23 | 01/14/23 |
| F12 | Players gets 1 more turn after a player scores above 6000 | D | 01/15/23 | 01/15/23 |
| F13 | New player strategy: Combo | D | 01/19/23 | 01/19/23 |
| F14 | Choose player strategy | D | 01/19/23 | 01/20/23 |
| F15 | Cards in Game | D | 01/21/23 | 01/21/23 |
| F16 | Have a deck of cards | D | 01/21/23 | 01/21/23 |
| F17 | Draw a card | D | 01/21/23 | 01/21/23 |
| F18 | new feature: favour saber during Sea Battle | D | 01/21/23 | 01/21/23 |
| ... | ... | ... |

