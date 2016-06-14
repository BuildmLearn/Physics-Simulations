package org.buildmlearn.physicssimulations;

import android.content.Intent;
import android.os.Bundle;

import org.buildmlearn.physicssimulations.utils.Constants;

public class SimulationsActivity extends NavigationActivity
        implements SimulationsFragment.OnSimulationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulations);
        super.onCreateDrawer(R.id.nav_simulations);
    }

    @Override
    public void onSimulationInteraction(Simulation simulation, Constants.TYPE type) {
        switch (type) {
            case DETAILS:
                Intent detailsIntent = new Intent(this, DetailsActivity.class);
                detailsIntent.putExtra(Constants.SIM_NAME, simulation.name);
                detailsIntent.putExtra(Constants.SIM_DETAILS, simulation.details);
                startActivity(detailsIntent);
                break;
            case SIM:
                Intent simIntent = new Intent(this, SimulationLauncherActivity.class);
                simIntent.putExtra(Constants.SIM_NAME, simulation.name);
                startActivity(simIntent);
                break;
            case TEST:
                Intent testIntent = new Intent(this, TestActivity.class);
                testIntent.putExtra(Constants.SIM_NAME, simulation.name);
                startActivity(testIntent);
                break;
            default:
                break;
        }
    }
}
