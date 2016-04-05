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

    private final static String LOG = SplashActivity.class.getSimpleName();

    private Context me;
    private View mRootView;
    private ImageView mLogo;
    private TextView mVersion;
    private DotProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        me = this;
        mRootView = findViewById(R.id.root_view);
        mLogo = (ImageView) findViewById(R.id.logo);
        mVersion = (TextView) findViewById(R.id.version);
        mProgress = (DotProgressBar) findViewById(R.id.dot_progress_bar);
        initLogo();
        initVersion();
        execute();
    }

    /**
     * Test
     */
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
        String version = getString(R.string.version_intro, this) + " ";
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
        Animation logoAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_logo_expand);
        mLogo.setVisibility(View.VISIBLE);
        mLogo.startAnimation(logoAnimation);
    }

    private void removeLogo() {
        Animation logoAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_logo_remove);
        logoAnimation.setAnimationListener(this);
        mLogo.startAnimation(logoAnimation);
        mLogo.setVisibility(View.GONE);

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
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        mProgress.setVisibility(View.GONE);
        Intent i = new Intent(me, MainActivity.class);
        MySharedPreferences sp = MyApplication.getInstance().getSP();
        if (sp.hasUser())
            i.putExtra(getString(R.string.intent_user), sp.getUser());
        startActivity(i);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
