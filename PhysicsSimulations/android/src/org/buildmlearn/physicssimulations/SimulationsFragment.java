package org.buildmlearn.physicssimulations;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.buildmlearn.physicssimulations.utils.Constants;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class SimulationsFragment extends Fragment {

    private OnSimulationListener simulationListener;

    public SimulationsFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simulations_list, container, false);

        Context context = view.getContext();
        final RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new SimulationsRecyclerViewAdapter(Constants.SIMULATION_LIST, simulationListener));

        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
                if (recyclerView.getChildCount() == 3) {
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                    boolean showTutorial = sharedPreferences.getBoolean(Constants.TUT_LIST, true);

                    if (!showTutorial)
                        return;

                    ShowcaseConfig config = new ShowcaseConfig();
                    config.setDelay(500);
                    config.setMaskColor(Constants.COLOR_MASK);
                    MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(getActivity());
                    sequence.setConfig(config);
                    sequence.addSequenceItem(recyclerView.getChildAt(2).findViewById(R.id.text_layout),
                            "Touch here for more details", "OK");
                    sequence.addSequenceItem(recyclerView.getChildAt(2).findViewById(R.id.test_button),
                            "You can test yourself", "NEXT");
                    sequence.addSequenceItem(recyclerView.getChildAt(2).findViewById(R.id.sim_button),
                            "Or you can play with simulation", "OK");
                    sequence.start();

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(Constants.TUT_LIST, false);
                    editor.apply();
                }
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {}
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSimulationListener) {
            simulationListener = (OnSimulationListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSimulationListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        simulationListener = null;
    }

    public interface OnSimulationListener {
        void onSimulationInteraction(Simulation simulation, Constants.TYPE type);
    }
}
