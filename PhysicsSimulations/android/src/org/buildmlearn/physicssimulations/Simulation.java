package org.buildmlearn.physicssimulations;

/**
 * The <code>Simulation</code> class describes an element from
 * simulations list
 * @author  Costin Giorgian
 */
public class Simulation {
    public final String name;
    public final String details;

    public Simulation(String content, String details) {
        this.name = content;
        this.details = details;
    }
}
