package org.buildmlearn.physicssimulations;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.facebook.shimmer.ShimmerFrameLayout;

import org.buildmlearn.physicssimulations.utils.Constants;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

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
//                Intent intent = new Intent(HomeActivity.this, SimulationsActivity.class);
//                startActivity(intent);
//                finish();
                Intent simIntent = new Intent(HomeActivity.this, SimulationLauncher.class);
                simIntent.putExtra(Constants.SIM_NAME, "Light Refraction");
                startActivity(simIntent);
            }
        });

        ShimmerFrameLayout shimmerFrameLayout =
                (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        shimmerFrameLayout.setDuration(3000);
        shimmerFrameLayout.startShimmerAnimation();

//        new MaterialShowcaseView.Builder(this)
//                .setTarget(button)
//                .setDismissText("GOT IT")
//                .setContentText("This button will take you to Simulations List")
//                .setDelay(500)
//                .setDismissOnTouch(true)
//                .setDismissOnTargetTouch(true)
//                .setMaskColour(Color.parseColor("#dd439DBB"))
//                .singleUse("home")
//                .show();
//        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
//
//        ShowcaseConfig config = new ShowcaseConfig();
//        config.setDelay(500);
//        config.setMaskColor(Constants.COLOR_MASK);
//        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this, "home");
//        sequence.setConfig(config);
//        sequence.addSequenceItem(button,
//                "This button will take you to Simulations List", "GOT IT");
//        for (int i = 0; i < toolbar.getChildCount(); i++)
//            if(toolbar.getChildAt(i) instanceof ImageButton)
//                sequence.addSequenceItem(toolbar.getChildAt(i),
//                        "From here you can navigate throw application", "OK");
//        sequence.start();
    }

}
