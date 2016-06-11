package org.buildmlearn.physicssimulations;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import org.buildmlearn.physicssimulations.utils.Constants;

public class SimulationLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.hideStatusBar = true;
		Intent intent = getIntent();
		String simName = intent.getStringExtra(Constants.SIM_NAME);

		View view = initializeForView(Constants.getSim(simName), config);

		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.addView(view);
		setContentView(linearLayout);
	}
}
