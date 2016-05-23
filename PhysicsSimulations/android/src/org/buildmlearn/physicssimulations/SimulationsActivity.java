package org.buildmlearn.physicssimulations;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import org.buildmlearn.physicssimulations.utils.Constants;

public class SimulationsActivity extends NavigationActivity
        implements SimulationsFragment.OnSimulationListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulations);
        super.onCreateDrawer();
    }

    @Override
    public void onSimulationInteraction(Simulation simulation, Constants.TYPE type) {
        switch (type) {
            case DETAILS:
                //Toast.makeText(this, "ITEM", Toast.LENGTH_SHORT).show();
                Intent detailsIntent = new Intent(this, DetailsActivity.class);
                detailsIntent.putExtra(Constants.SIM_NAME, simulation.name);
                detailsIntent.putExtra(Constants.SIM_DETAILS, simulation.details);
                startActivity(detailsIntent);
                break;
            case SIM:
                //Toast.makeText(this, "SIM", Toast.LENGTH_SHORT).show();
                Intent simIntent = new Intent(this, SimulationLauncher.class);
                simIntent.putExtra(Constants.SIM_NAME, simulation.name);
                startActivity(simIntent);
                break;
            case TEST:
                //Toast.makeText(this, "TEST", Toast.LENGTH_SHORT).show();
                Intent testIntent = new Intent(this, TestActivity.class);
                testIntent.putExtra(Constants.SIM_NAME, simulation.name);
                startActivity(testIntent);
                break;
            default:
                break;
        }
    }
}
