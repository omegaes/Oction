package com.mega4tech.oction;

import android.support.annotation.NonNull;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.mega4tech.oction.injection.AppComponent;
import com.mega4tech.oction.injection.AppModule;
import com.mega4tech.oction.injection.DaggerAppComponent;
import com.mega4tech.oction.utils.timber.CrashReportTree;
import com.squareup.leakcanary.LeakCanary;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;


public class Application extends android.app.Application{


    private AppComponent mAppComponent;

    @NonNull
    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    @Override
    public  void onCreate(){
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);


        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        //Crashlytics
        CrashlyticsCore core = new CrashlyticsCore.Builder()
                .disabled(BuildConfig.DEBUG)
                .build();

        Fabric.with(this, new Crashlytics.Builder().core(core).build());

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportTree());
        }
    }

}
