package org.buildmlearn.physicssimulations.utils;

import android.util.Log;

import org.buildmlearn.physicssimulations.Simulation;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static final List<Simulation> SIMULATION_LIST = new ArrayList<Simulation>(){
        {
            add(new Simulation("Atwood","Simulation Details"));
            add(new Simulation("Circuit Builder","Simulation Details"));
            add(new Simulation("Collision","Simulation Details"));
            add(new Simulation("Lens","Simulation Details"));
            add(new Simulation("Interference","Simulation Details"));
            add(new Simulation("Pendulum","Simulation Details"));
            add(new Simulation("Projectile",""));
            add(new Simulation("Refraction",""));
            add(new Simulation("Spring",""));
            add(new Simulation("Wave",""));
        }
    };
}
