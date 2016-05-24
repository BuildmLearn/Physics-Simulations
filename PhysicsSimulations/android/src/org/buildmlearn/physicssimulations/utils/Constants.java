package org.buildmlearn.physicssimulations.utils;

import org.buildmlearn.physicssimulations.Atwood;
import org.buildmlearn.physicssimulations.CircuitBuilder;
import org.buildmlearn.physicssimulations.Collision;
import org.buildmlearn.physicssimulations.Lens;
import org.buildmlearn.physicssimulations.LightInterference;
import org.buildmlearn.physicssimulations.LightRefraction;
import org.buildmlearn.physicssimulations.Pendulum;
import org.buildmlearn.physicssimulations.Projectile;
import org.buildmlearn.physicssimulations.R;
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

    public static int getIcon(String name) {
        if (name.equals("Atwood"))
            return R.drawable.pulley;
        if (name.equals("Circuit Builder"))
            return R.drawable.battery;
        if (name.equals("Collision"))
            return R.drawable.collision;
        if (name.equals("Lens"))
            return R.drawable.lens;
        if (name.equals("Light Interference"))
            return R.drawable.bulb;
        if (name.equals("Light Refraction"))
            return R.drawable.flashlight;
        if (name.equals("Pendulum"))
            return R.drawable.pendulum;
        if (name.equals("Projectile"))
            return R.drawable.projectile;
        if (name.equals("Spring"))
            return R.drawable.spring;
        if (name.equals("Wave"))
            return R.drawable.wave;
        return 0;
    }

    public static final String TEST_PROBLEM = "Monochromatic yellow light (λ = 594 nm) passes throught " +
            "two slits with a slit spacing of 0.125 mm and forms an interference pattern on a screen that" +
            "is positioned 14.5 m away. Determine the distance between the fifth bright spots on opposite " +
            "sides of the central bright spot.\n";
    public static final String CORECT_ANSWER = "68.9 cm";
    public static final String ANSWER_1 = "71.5 cm";
    public static final String ANSWER_2 = "67.8 cm";
    public static final String ANSWER_3 = "72.9 cm";

}
