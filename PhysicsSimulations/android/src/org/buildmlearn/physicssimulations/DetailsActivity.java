package org.buildmlearn.physicssimulations;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.buildmlearn.physicssimulations.utils.Constants;

import io.github.kexanie.library.MathView;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

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

        final int idSim = Constants.getId(simName);

        MathView mathView = (MathView) findViewById(R.id.math_view);
        mathView.setText(Constants.DETAILS[idSim]);

//        TextView textView = (TextView) findViewById(R.id.details_text_view);
//        textView.setText(Html.fromHtml(Constants.DETAILS[idSim]));

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
                Intent simIntent = new Intent(DetailsActivity.this, SimulationLauncherActivity.class);
                simIntent.putExtra(Constants.SIM_NAME, simName);
                startActivity(simIntent);
            }
        });

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean showTutorial = sharedPreferences.getBoolean(Constants.TUT_DETAILS, true);
        if (showTutorial) {
            ShowcaseConfig config = new ShowcaseConfig();
            config.setDelay(500);
            config.setMaskColor(Constants.COLOR_MASK);
            MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this);
            sequence.setConfig(config);
            sequence.addSequenceItem(testFab,
                    "Here you can check your understanding", "GOT IT");
            sequence.addSequenceItem(simFab,
                    "This button will take you to the simulation", "OK");
            sequence.start();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(Constants.TUT_DETAILS, false);
            editor.apply();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
