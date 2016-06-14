package org.buildmlearn.physicssimulations;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication;

import org.buildmlearn.physicssimulations.utils.Constants;

public class SimulationLauncherFragment extends AndroidFragmentApplication {

    public SimulationLauncherFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        Intent intent = getActivity().getIntent();
        String simName = intent.getStringExtra(Constants.SIM_NAME);

        return initializeForView(Constants.getSim(simName), config);
    }

}
