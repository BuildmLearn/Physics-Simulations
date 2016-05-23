package org.buildmlearn.physicssimulations.utils;

import org.buildmlearn.physicssimulations.Atwood;
import org.buildmlearn.physicssimulations.CircuitBuilder;
import org.buildmlearn.physicssimulations.Collision;
import org.buildmlearn.physicssimulations.Lens;
import org.buildmlearn.physicssimulations.LightInterference;
import org.buildmlearn.physicssimulations.LightRefraction;
import org.buildmlearn.physicssimulations.Pendulum;
import org.buildmlearn.physicssimulations.Projectile;
import org.buildmlearn.physicssimulations.Simulation;
import org.buildmlearn.physicssimulations.SimulationType;
import org.buildmlearn.physicssimulations.Spring;
import org.buildmlearn.physicssimulations.Wave;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static final List<Simulation> SIMULATION_LIST = new ArrayList<Simulation>(){
        {
            add(new Simulation("Atwood",
                    "Simulation Details"));
            add(new Simulation("Circuit Builder",
                    "Simulation Details"));
            add(new Simulation("Collision",
                    "Simulation Details"));
            add(new Simulation("Lens",
                    "Simulation Details"));
            add(new Simulation("Light Interference",
                    "Simulation Details"));
            add(new Simulation("Light Refraction",
                    "Simulation Details"));
            add(new Simulation("Pendulum",
                    "Simulation Details"));
            add(new Simulation("Projectile",
                    "Simulation Details"));
            add(new Simulation("Spring","" +
                    "Simulation Details"));
            add(new Simulation("Wave",
                    "Simulation Details"));
        }
    };

    public enum TYPE {
        SIM, TEST, DETAILS
    }

    public static final String SIM_NAME = "sim_name";
    public static final String SIM_DETAILS = "sim_details";

    public static SimulationType getSim(String name) {
        if (name.equals("Atwood"))
            return new Atwood();
        if (name.equals("Circuit Builder"))
            return new CircuitBuilder();
        if (name.equals("Collision"))
            return new Collision();
        if (name.equals("Lens"))
            return new Lens();
        if (name.equals("Light Interference"))
            return new LightInterference();
        if (name.equals("Light Refraction"))
            return new LightRefraction();
        if (name.equals("Pendulum"))
            return new Pendulum();
        if (name.equals("Projectile"))
            return new Projectile();
        if (name.equals("Spring"))
            return new Spring();
        if (name.equals("Wave"))
            return new Wave();
        return null;
    }
}
