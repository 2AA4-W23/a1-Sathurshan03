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
| F19 | sea battle cards have specific number of saber to roll | D | 01/21/23 | 01/21/23 |
| F20 | award points if successful Sea Battle play | D | 01/21/23 | 01/21/23 |
| F21 | New Money Business Card | D | 01/21/23 | 01/21/23 |
| F22 | Add Money Business Card to deck | D | 01/21/23 | 01/21/23 |
| F23 | implement Monkey Busines Card effect | D | 01/21/23 | 01/21/23 |
| F24 | Calculate score for monkey business card | D | 01/21/23 | 01/21/23 |
| F25 | New Gold and Diamond Card | D | 01/24/23 | 01/24/23 |
| F26 | add Gold and Diamond Card to deck | B(F25) |  |  |
| F27 | Implement Gold and Diamond Card effect | B(F26) |  |  |
| F28 | Calculate score for Gold and Diamond Card | B(F27) |  |  |
| F29 | New Captain Card | D | 01/24/23 | 01/24/23 |
| F30 | add Captain Card to deck| B(F29) |  |  |
| F31 | Implement New Captain Card effect | B(F30) |  |  |
| F32 | Calculate score for New Captain Card | B(F31) |  |  |
| F33 | New Skull Card | D | 01/24/23 | 01/24/23 |
| F34 | add Skull Card to deck| B(F33) |  |  |
| F35 | Implement New Skull Card effect | B(F34) |  |  |
| F36 | Calculate score for Skull Card | B(F35) |  |  |
| F37 | New Sorceress Card | D | 01/24/23 | 01/24/23 |
| F38 | add Sorceress Card to deck | B(F37) |  |  |
| F39 | Implement New Sorceress Card effect | B(F38) |  |  |
| F40 | Calculate score for Sorceress Card | B(F39) |  |  |
| F41 | New Tressure Chest Card | P |  |  |
| F42 | add Tressure Chest Card to deck | B(F41) |  |  |
| F43 | Implement New Tressure Chest Card effect | B(F42) |  |  |
| F44 | Calculate score for Tressure Chest Card | B(F43) |  |  |
| F45 | Create Island of Skulls mode | P |  |  |
| F46 | Move player to Island of Skulls mode when 4 or more skulls in initial roll | B(F45) |  |  |
| F47 | Island of Skulls mode ends when less than 1 skull is rolled | B(F45) |  |  |
| F48 | Deduct points depending on outcome of Islands of Skulls | B(F45) |  |  |
| ... | ... | ... |

