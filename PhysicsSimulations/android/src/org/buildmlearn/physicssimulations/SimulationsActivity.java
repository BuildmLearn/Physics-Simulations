package org.buildmlearn.physicssimulations;

import android.os.Bundle;

public class SimulationsActivity extends NavigationActivity
        implements SimulationsFragment.OnSimulationListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulations);
        super.onCreateDrawer();
    }

    @Override
    public void onSimulationInteraction(Simulation simulation) {

    }
}
