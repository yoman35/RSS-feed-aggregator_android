package yb.rssfeedaggregator.main_activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import yb.rssfeedaggregator.R;
import yb.rssfeedaggregator.models.User;
import yb.rssfeedaggregator.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    @SuppressWarnings("unused")
    private static final String LOG = MainActivity.class.getSimpleName();

    private static final int
            DRAWER_OPEN = R.string.drawer_open,
            DRAWER_CLOSE = R.string.drawer_close,
            LAYOUT = R.layout.activity_main,
            CONTENT = R.id.content_layout,
            MENU = R.id.navigation_drawer,
            TOOLBAR = R.id.toolbar,
            DRAWER = R.id.root_view,
            SETTINGS = R.id.action_settings,
            WELCOME = R.string.welcome,
            INTENT_USER = R.string.intent_user;

    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private MenuFragment mMenuFragment;
    private DrawerLayout mMenu;
    private FragmentManager mFragmentManager;
    private User mUser;
    private NotLoggedInFragment mUnknownFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        initVars();
        setToolbar();
        setNavigationDrawer();
        setContentFragment(mUser, new MainFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item))
            return true;
        else if (item.getItemId() == SETTINGS)
            startActivity(new Intent(this, SettingsActivity.class));
        return super.onOptionsItemSelected(item);
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

    private void initVars() {
        mUser = getIntent().getParcelableExtra(getString(INTENT_USER));
        mFragmentManager = getSupportFragmentManager();
        mToolbar = (Toolbar) findViewById(TOOLBAR);
        mMenu = (DrawerLayout) findViewById(DRAWER);
        mDrawerToggle = setUpDrawerToggle();
        mMenuFragment = new MenuFragment();
        mUnknownFragment = new NotLoggedInFragment();
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        setToolbarTitle(getString(WELCOME));
    }

    private void setNavigationDrawer() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(MENU, mMenuFragment)
                .commit();
        mMenu.addDrawerListener(mDrawerToggle);
    }

    /**
     * Set the appropriate fragment in {@link MainActivity}
     *
     * @param user     the current user to check
     * @param fragment the instantiated fragment to put in {@link MainActivity}
     */
    public void setContentFragment(User user, Fragment fragment) {
        if (fragment instanceof MainFragment && user == null) {
            changeContentFragment(mUnknownFragment);
            return;
        }
        changeContentFragment(fragment);
    }

    /**
     * Set the fragment in {@link MainActivity} to {@link HelpFragment}
     */
    public void setContentFragmentToHelp() {
        changeContentFragment(new HelpFragment());
    }

    private ActionBarDrawerToggle setUpDrawerToggle() {
        return new ActionBarDrawerToggle(this, mMenu, mToolbar, DRAWER_OPEN, DRAWER_CLOSE);
    }

    private void switchToLogged() {
        changeContentFragment(new MainFragment());
        mMenuFragment.switchToLogged();
    }

    private void switchToUnknownUser() {
        changeContentFragment(mUnknownFragment);
        mMenuFragment.switchToUnknownUser();
        //TODO: adjust layout according to the user's preferences
    }

    private void changeContentFragment(Fragment fragment) {
        mFragmentManager.beginTransaction()
                .replace(CONTENT, fragment)
                .commit();
    }

    /**
     * Click event on the menu's back button
     *
     * @param view is an ImageView
     */
    public void onClickOnBackButton(View view) {
        closeDrawer();
    }

    /**
     * Click event on the connect button
     *
     * @param view is an FButton
     */
    public void onClickOnConnectButton(View view) {
        closeDrawer();
        //TODO: open a LoginDialogFragment
    }

    public void closeDrawer() {
        if (mMenu.isDrawerOpen(GravityCompat.START))
            mMenu.closeDrawers();
    }

    public void setToolbarTitle(String title) {
        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle(title);
    }
}
