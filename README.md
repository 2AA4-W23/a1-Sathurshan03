# A1 - Piraten Karpen

  * Author: < You name here >
  * Email: < Your email here >

## Build and Execution

  * To clean your working directory:
    * `mvn clean`
  * To compile the project:
    * `mvn compile`
  * To run the project in development mode:
    * `mvn -q exec:java` (here, `-q` tells maven to be _quiet_)
  * To package the project as a turn-key artefact:
    * `mvn package`
  * To run the packaged delivery:
    * `java -jar target/piraten-karpen-jar-with-dependencies.jar` 

Remark: **We are assuming here you are using a _real_ shell (e.g., anything but PowerShell on Windows)**

## Feature Backlog

 * Status: 
   * Pending (P), Started (S), Blocked (B), Done (D)
 * Definition of Done (DoD):
   * A feature is marked as "Done" whenever the implementation successfully satisfies the minimum requirement of that feature. The implemented feature must not alter the status and outcome of previously implemented features. 

### Backlog 

| MVP? | Id  | Feature  | Status  |  Started  | Delivered |
| :-:  |:-:  |---       | :-:     | :-:       | :-:       |
| x   | F01 | Roll a dice |  D | 01/12/23 | 01/12/23 |
| x   | F02 | Roll eight dices  |  D | 01/12/23  | 01/12/23 |
| x   | F03 | Run 42 games in simulation  |  D  |  01/12/23 | 01/12/23 |
| x   | F04 | end of game with three cranes | D | 01/12/23 | 01/12/23 |
| x   | F05 | Player keeping random number of dice at their turn | D | 01/12/23 | 01/12/23 | 
| x   | F06 | Score points: 3 or more of a kind | D | 01/12/23 | 01/12/23 |
| x   | F07 | Score points: gold coins and diamonds | B (F04) | | 
| x   | F08 | Output percent wins at end of simulation | B (F04) | | 
| ... | ... | ... |

