package org.buildmlearn.physicssimulations;

import android.os.Bundle;

public class SettingsActivity extends NavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        super.onCreateDrawer(R.id.nav_settings);

        if (findViewById(R.id.fragment_container) != null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                    new SettingsFragment()).commit();
        }
    }
}
