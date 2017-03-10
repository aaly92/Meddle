package io.intrepid.meddle;

import android.os.AsyncTask;

import org.mockito.Mockito;

import io.intrepid.meddle.base.PresenterConfiguration;
import io.intrepid.meddle.logging.CrashReporter;
import io.intrepid.meddle.models.TokenStorage;
import io.intrepid.meddle.rest.RestApi;
import io.intrepid.meddle.rest.RetrofitClient;
import io.intrepid.meddle.rest.SpotifyApi;
import io.intrepid.meddle.rest.SpotifyRetrofitClient;
import io.intrepid.meddle.settings.SharePreferencesManager;
import io.intrepid.meddle.settings.UserSettings;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InstrumentationTestApplication extends MeddleApplication {
    private static RestApi restApiOverride = null;
    private static SpotifyApi spotifyApiOverride = null;

    private static UserSettings userSettingsOverride = null;
    private static TokenStorage tokenStorage;

    @Override
    public PresenterConfiguration getPresenterConfiguration() {
        return new PresenterConfiguration(
                // using AsyncTask executor since Espresso automatically waits for it to clear before proceeding
                Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR),
                AndroidSchedulers.mainThread(),
                userSettingsOverride != null ? userSettingsOverride : SharePreferencesManager.getInstance(this),
                restApiOverride != null ? restApiOverride : RetrofitClient.getApi(),
                Mockito.mock(CrashReporter.class), spotifyApiOverride != null ? spotifyApiOverride : SpotifyRetrofitClient.getApi());
    }

    public static void overrideRestApi(RestApi restApi) {
        restApiOverride = restApi;
    }

    public static void clearRestApiOverride() {
        restApiOverride = null;
    }

    public static void overrideUserSettings(UserSettings userSettings) {
        userSettingsOverride = userSettings;
    }

    public static void clearUserSettingsOverride() {
        userSettingsOverride = null;
    }
}
