package com.example.pawan.androidtemplate;

import android.support.multidex.MultiDexApplication;
import android.text.SpannableStringBuilder;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.example.pawan.androidtemplate.dependencies.AppComponent;
import com.example.pawan.androidtemplate.dependencies.DaggerAppComponent;
import com.squareup.leakcanary.LeakCanary;

import java.io.PrintWriter;
import java.io.StringWriter;

import timber.log.Timber;

public class TemplateApp extends MultiDexApplication {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                return;
            }
            LeakCanary.install(this);

            //Install LeakCanary
            Timber.plant(new Timber.DebugTree());

        } else {
            Timber.plant(new Timber.Tree() {

                @Override
                protected void log(int priority, String tag, String message, Throwable throwable) {
                    if (priority == Log.ERROR || priority == Log.WARN) {
                        Crashlytics.log(priority, tag, message);

                        if (throwable != null) {
                            Crashlytics.logException(throwable);
                        }
                    }
                }
            });

            Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    SpannableStringBuilder ssb = new SpannableStringBuilder();
                    ssb.append("Uncaught Exception: ");
                    ssb.append(e.getLocalizedMessage());
                    ssb.append("\nStack Trace:\n");

                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    e.printStackTrace(pw);
                    ssb.append(sw.toString());

                    Crashlytics.log(Log.ERROR, "Uncaught Exception", ssb.toString());
                    Crashlytics.logException(e);
                }
            });
        }
        initDagger();
    }

    private void initDagger() {
        mAppComponent = DaggerAppComponent.builder()
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
