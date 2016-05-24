package org.buildmlearn.physicssimulations;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.buildmlearn.physicssimulations.utils.Constants;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        final String simName = intent.getStringExtra(Constants.SIM_NAME);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(simName);
        setSupportActionBar(toolbar);

        final FloatingActionButton testFab = (FloatingActionButton) findViewById(R.id.test_fab);
        testFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent testIntent = new Intent(DetailsActivity.this, TestActivity.class);
                testIntent.putExtra(Constants.SIM_NAME, simName);
                startActivity(testIntent);
            }
        });

        final FloatingActionButton simFab = (FloatingActionButton) findViewById(R.id.sim_fab);
        simFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent simIntent = new Intent(DetailsActivity.this, SimulationLauncher.class);
                simIntent.putExtra(Constants.SIM_NAME, simName);
                startActivity(simIntent);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
