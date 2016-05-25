package org.buildmlearn.physicssimulations;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.buildmlearn.physicssimulations.SimulationsFragment.OnSimulationListener;
import org.buildmlearn.physicssimulations.utils.Constants;

import java.util.List;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class SimulationsRecyclerViewAdapter extends RecyclerView.Adapter<SimulationsRecyclerViewAdapter.ViewHolder> {

    private final List<Simulation> simulationList;
    private final OnSimulationListener simulationListener;

    public SimulationsRecyclerViewAdapter(List<Simulation> list, OnSimulationListener listener) {
        simulationList = list;
        simulationListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_simulations, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.simulation = simulationList.get(position);
        holder.icon.setImageResource(Constants.getIcon(holder.simulation.name));
        holder.nameView.setText(simulationList.get(position).name);
        holder.detailsView.setText(simulationList.get(position).details);
    }

    @Override
    public int getItemCount() {
        return simulationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final View mView;
        public final ImageView icon;
        public final Button simButton;
        public final Button testButton;
        public final TextView nameView;
        public final TextView detailsView;
        public Simulation simulation;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            icon        = (ImageView) view.findViewById(R.id.icon);
            nameView    = (TextView) view.findViewById(R.id.name);
            detailsView = (TextView) view.findViewById(R.id.details);
            simButton   = (Button) view.findViewById(R.id.sim_button);
            testButton  = (Button) view.findViewById(R.id.test_button);

            mView.setOnClickListener(this);
            simButton.setOnClickListener(this);
            testButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.sim_button:
                    simulationListener.onSimulationInteraction(this.simulation, Constants.TYPE.SIM);
                    break;
                case R.id.test_button:
                    simulationListener.onSimulationInteraction(this.simulation, Constants.TYPE.TEST);
                    break;
                default:
                    simulationListener.onSimulationInteraction(this.simulation, Constants.TYPE.DETAILS);
                    break;
            }
        }

        @Override
        public String toString() {
            return super.toString() + " '" + detailsView.getText() + "'";
        }
    }
}
