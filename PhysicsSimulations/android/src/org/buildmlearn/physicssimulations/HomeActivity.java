package org.buildmlearn.physicssimulations;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.shimmer.ShimmerFrameLayout;

public class HomeActivity extends NavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        super.onCreateDrawer(R.id.nav_home);
        Button button = (Button) findViewById(R.id.sims_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SimulationsActivity.class);
                startActivity(intent);
                finish();
//                Intent simIntent = new Intent(HomeActivity.this, SimulationLauncherActivity.class);
//                simIntent.putExtra(Constants.SIM_NAME, "Lens");
//                startActivity(simIntent);
            }
        });

        ShimmerFrameLayout shimmerFrameLayout =
                (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        shimmerFrameLayout.setDuration(3000);
        shimmerFrameLayout.startShimmerAnimation();
    }

}
