package xhb.xha.com.networkapplication;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import xhb.xha.com.networkapplication.code.DataManager;
import xhb.xha.com.networkapplication.di.module.AppModule;
import xhb.xha.com.networkapplication.di.module.HttpModule;

/**
 * Created by ysq on 2019/4/26.
 */

public class Mpp extends Application implements HasActivityInjector{

    private static Context context;
    @Inject
    DispatchingAndroidInjector<Activity> mAndroidInjector;

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mAndroidInjector;
    }


    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

    }

}
