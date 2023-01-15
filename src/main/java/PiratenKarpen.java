import pk.Simulation;

public class PiratenKarpen {

    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");
        Simulation simulation = new Simulation(42, 2);
        simulation.runSimulation();
        System.out.println("\nThat's all folks!");
    }
    
}
