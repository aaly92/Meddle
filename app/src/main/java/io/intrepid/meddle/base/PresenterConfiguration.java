package io.intrepid.meddle.base;

import android.support.annotation.NonNull;

import io.intrepid.meddle.logging.CrashReporter;
import io.intrepid.meddle.rest.RestApi;
import io.intrepid.meddle.rest.SpotifyApi;
import io.intrepid.meddle.settings.UserSettings;
import rx.Scheduler;

/**
 * Wrapper class for common dependencies that all presenters are expected to have
 */
public class PresenterConfiguration {
    @NonNull
    private final Scheduler ioScheduler;
    @NonNull
    private final Scheduler uiScheduler;
    @NonNull
    private final UserSettings userSettings;
    @NonNull
    private final RestApi restApi;
    @NonNull
    private final SpotifyApi spotifyApi;
    @NonNull
    private final CrashReporter crashReporter;


    public PresenterConfiguration(@NonNull Scheduler ioScheduler,
                                  @NonNull Scheduler uiScheduler,
                                  @NonNull UserSettings userSettings,
                                  @NonNull RestApi restApi,
                                  @NonNull CrashReporter crashReporter,
                                  @NonNull SpotifyApi spotifyApi) {
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
        this.userSettings = userSettings;
        this.spotifyApi = spotifyApi;
        this.restApi = restApi;
        this.crashReporter = crashReporter;
    }

    @NonNull
    public Scheduler getIoScheduler() {
        return ioScheduler;
    }

    @NonNull
    public Scheduler getUiScheduler() {
        return uiScheduler;
    }

    @NonNull
    public UserSettings getUserSettings() {
        return userSettings;
    }

    @NonNull
    public RestApi getRestApi() {
        return restApi;
    }

    @NonNull
    public SpotifyApi getSpotifyApi() {
        return spotifyApi;
    }

    @NonNull
    public CrashReporter getCrashReporter() {
        return crashReporter;
    }
}
