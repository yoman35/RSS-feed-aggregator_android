package yb.rssfeedaggregator.main_activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import yb.rssfeedaggregator.R;
import yb.rssfeedaggregator.models.User;
import yb.rssfeedaggregator.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    private static final int
            drawerOpenId = R.string.drawer_open,
            drawerCloseId = R.string.drawer_close;

    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mMenu;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mMenu = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        setToolbar();
        setNavigationDrawer();
        setFragment();
    }

    private void setFragment() {
        User user = getIntent().getParcelableExtra(getString(R.string.intent_user));
        int container = R.id.content_layout;
        if (user == null) {
            mFragmentManager.beginTransaction()
                    .replace(container, new NotLoggedInFragment())
                    .commit();
        }
        //TODO: change to real fragment when user is known
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle(getString(R.string.welcome));
    }

    private void setNavigationDrawer() {
        mDrawerToggle = setUpDrawerToggle();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.navigation_drawer, new MenuFragment())
                .commit();
        mMenu.addDrawerListener(mDrawerToggle);
    }

    private ActionBarDrawerToggle setUpDrawerToggle() {
        return new ActionBarDrawerToggle(this, mMenu, mToolbar, drawerOpenId, drawerCloseId);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        } else if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
