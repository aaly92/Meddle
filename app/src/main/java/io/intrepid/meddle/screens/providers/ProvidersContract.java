package io.intrepid.meddle.screens.providers;

import android.content.Intent;

import com.spotify.sdk.android.authentication.AuthenticationRequest;

import io.intrepid.meddle.base.BaseContract;

public class ProvidersContract {
    public interface View extends BaseContract.View {
        void openLoginSpotifyLoginActivity(int requestCode, AuthenticationRequest request);

        void launchNextActivity();

        void launchNotPremiumAccountScreen();
    }

    public interface Presenter extends BaseContract.Presenter<ProvidersContract.View> {
        void openSpotifyLogin();

        void processSpotifyResponse(int requestCode, int resultCode, Intent intent);
    }
}
