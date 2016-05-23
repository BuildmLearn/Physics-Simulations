package org.buildmlearn.physicssimulations;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import org.buildmlearn.physicssimulations.utils.Constants;

public class SimulationLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		Intent intent = getIntent();
		String simName = intent.getStringExtra(Constants.SIM_NAME);
		initialize(Constants.getSim(simName), config);
	}
}
