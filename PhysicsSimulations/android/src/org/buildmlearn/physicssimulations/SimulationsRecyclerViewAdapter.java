package org.buildmlearn.physicssimulations;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.buildmlearn.physicssimulations.SimulationsFragment.OnSimulationListener;
import java.util.List;

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
        holder.nameView.setText(simulationList.get(position).name);
        holder.detailsView.setText(simulationList.get(position).details);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != simulationListener) {
                    simulationListener.onSimulationInteraction(holder.simulation);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return simulationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView nameView;
        public final TextView detailsView;
        public Simulation simulation;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            nameView = (TextView) view.findViewById(R.id.name);
            detailsView = (TextView) view.findViewById(R.id.details);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + detailsView.getText() + "'";
        }
    }
}
