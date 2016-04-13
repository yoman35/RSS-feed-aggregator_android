package yb.rssfeedaggregator.splash_activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.silvestrpredko.dotprogressbar.DotProgressBar;

import yb.rssfeedaggregator.R;
import yb.rssfeedaggregator.main_activity.MainActivity;
import yb.rssfeedaggregator.utils.MyApplication;
import yb.rssfeedaggregator.utils.MySharedPreferences;

public class SplashActivity extends AppCompatActivity implements Animation.AnimationListener {

    @SuppressWarnings("unused")
    private final static String LOG = SplashActivity.class.getSimpleName();

    private static final int
            LAYOUT = R.layout.activity_splash,
            ROOT_VIEW = R.id.root_view,
            LOGO = R.id.logo,
            VERSION = R.id.version,
            PROGRESS = R.id.dot_progress_bar,
            WELCOME = R.id.welcome,
            TITLE = R.id.app_name,
            ANIM_LOGO_EXPAND = R.anim.splash_logo_expand,
            ANIM_LOGO_REMOVE = R.anim.splash_logo_remove,
            ANIM_FADE_OUT = R.anim.fade_out,
            VERSION_INTRO = R.string.version_intro,
            INTENT_USER = R.string.intent_user;

    private Context me;
    private View mRootView;
    private ImageView mLogo;
    private TextView mVersion;
    private DotProgressBar mProgress;
    private TextView mWelcome;
    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        initVars();
        initLogo();
        initVersion();
        execute();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            mRootView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            mRootView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        int gone = View.GONE;
        mWelcome.setVisibility(gone);
        mLogo.setVisibility(gone);
        mProgress.setVisibility(gone);
        startMainActivity();
    }

    @Override
    public void onAnimationStart(Animation animation) {
        //Needed by interface
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        //Needed by interface
    }

    private void initVars() {
        me = this;
        mRootView = findViewById(ROOT_VIEW);
        mLogo = (ImageView) findViewById(LOGO);
        mVersion = (TextView) findViewById(VERSION);
        mProgress = (DotProgressBar) findViewById(PROGRESS);
        mWelcome = (TextView) findViewById(WELCOME);
        mTitle = (TextView) findViewById(TITLE);
    }

    private void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Log.e(LOG, "Thread interrupted", e);
                    onPause();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        removeLogo();
                    }
                });
            }
        }).start();
    }

    private void initVersion() {
        String version = getString(VERSION_INTRO) + " ";
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            version += pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(LOG, "Cannot retrieve application version code", e);
            version += "?.?";
        } finally {
            mVersion.setText(version);
        }
    }

    private void initLogo() {
        Animation logoAnimation = AnimationUtils.loadAnimation(this, ANIM_LOGO_EXPAND);
        mLogo.setVisibility(View.VISIBLE);
        mLogo.startAnimation(logoAnimation);
    }

    private void removeLogo() {
        Animation logoAnimation = AnimationUtils.loadAnimation(this, ANIM_LOGO_REMOVE);
        Animation fadeOut = AnimationUtils.loadAnimation(this, ANIM_FADE_OUT);
        logoAnimation.setAnimationListener(this);
        mLogo.startAnimation(logoAnimation);
        mWelcome.startAnimation(fadeOut);
        mVersion.startAnimation(fadeOut);
        mProgress.startAnimation(fadeOut);
        mTitle.startAnimation(fadeOut);
    }

    private void startMainActivity() {
        Intent i = new Intent(me, MainActivity.class);
        MySharedPreferences sp = MyApplication.getInstance().getSP();
        if (sp.hasUser())
            i.putExtra(getString(INTENT_USER), sp.getUser());
        startActivity(i);
    }
}
