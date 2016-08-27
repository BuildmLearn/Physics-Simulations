package org.buildmlearn.physicssimulations;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

/**
 * The {@link PreferenceFragmentCompat} for settings
 * @author  Costin Giorgian
 */
public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.preferences);
    }
}