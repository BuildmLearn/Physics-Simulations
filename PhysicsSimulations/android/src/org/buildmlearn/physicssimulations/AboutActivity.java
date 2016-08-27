package org.buildmlearn.physicssimulations;

import android.os.Bundle;

/**
 * About App screen
 * @author  Costin Giorgian
 */
public class AboutActivity extends NavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        super.onCreateDrawer(R.id.nav_about);
    }
}
