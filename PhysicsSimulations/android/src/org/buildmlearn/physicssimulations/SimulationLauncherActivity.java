package org.buildmlearn.physicssimulations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;

import org.buildmlearn.physicssimulations.utils.Constants;

public class SimulationLauncherActivity extends AppCompatActivity implements AndroidFragmentApplication.Callbacks {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simulation_launcher);

		Intent intent = getIntent();
		final String simName = intent.getStringExtra(Constants.SIM_NAME);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle(simName);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public void exit() {}
}
