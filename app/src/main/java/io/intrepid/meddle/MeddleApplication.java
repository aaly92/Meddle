package io.intrepid.meddle;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import io.intrepid.meddle.base.PresenterConfiguration;
import io.intrepid.meddle.logging.CrashlyticsReporter;
import io.intrepid.meddle.logging.TimberConfig;
import io.intrepid.meddle.rest.RetrofitClient;
import io.intrepid.meddle.rest.SpotifyRetrofitClient;
import io.intrepid.meddle.settings.SharePreferencesManager;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeddleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LeakCanary.install(this);

        CrashlyticsReporter.init(this);

        TimberConfig.init(CrashlyticsReporter.getInstance());
    }

    public PresenterConfiguration getPresenterConfiguration() {
        return new PresenterConfiguration(
                Schedulers.io(),
                AndroidSchedulers.mainThread(),
                SharePreferencesManager.getInstance(this),
                RetrofitClient.getApi(),
                CrashlyticsReporter.getInstance(),
                SpotifyRetrofitClient.getApi()
        );
    }
}
