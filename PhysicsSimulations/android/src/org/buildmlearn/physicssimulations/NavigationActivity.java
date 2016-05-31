package org.buildmlearn.physicssimulations;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.buildmlearn.physicssimulations.utils.Constants;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    Toolbar toolbar;
    protected void onCreateDrawer(int itemId) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(itemId);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        switch (id) {
            case R.id.nav_simulations:
                if (this instanceof SimulationsActivity)
                    break;
                intent = new Intent(this, SimulationsActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_home:
                if (this instanceof HomeActivity)
                    break;
                intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_settings:
                if (this instanceof SettingsActivity)
                    break;
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_how:
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
                editor.putBoolean(Constants.TUT_TEST, true);
                editor.putBoolean(Constants.TUT_LIST, true);
                editor.putBoolean(Constants.TUT_DETAILS, true);
                editor.apply();

                intent = new Intent(this, SimulationsActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_about:
                if (this instanceof AboutActivity)
                    break;
                intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_more:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://buildmlearn.org/"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                break;
            default:
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
