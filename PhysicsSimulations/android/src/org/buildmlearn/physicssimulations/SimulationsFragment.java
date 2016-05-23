package org.buildmlearn.physicssimulations;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.buildmlearn.physicssimulations.utils.Constants;

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

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new SimulationsRecyclerViewAdapter(Constants.SIMULATION_LIST, simulationListener));
        }
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
