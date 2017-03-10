package io.intrepid.meddle.screens.providers;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import io.intrepid.meddle.base.BasePresenter;
import io.intrepid.meddle.base.PresenterConfiguration;
import io.intrepid.meddle.models.TokenStorage;
import io.intrepid.meddle.rest.SpotifyApi;
import io.intrepid.meddle.rest.SpotifyRetrofitClient;
import io.intrepid.meddle.utils.RxUtils;
import rx.Subscription;
import timber.log.Timber;

public class ProvidersPresenter extends BasePresenter<ProvidersContract.View> implements ProvidersContract.Presenter {
    private static final String REDIRECT_URI = "http://google.com";
    private static final String CLIENT_ID = "ccb5dba6378e49da9b19aae45e166fce";
    private static final int REQUEST_CODE = 222;
    private static final String PREMIUM = "premium";

    ProvidersPresenter(@NonNull ProvidersContract.View view,
                       @NonNull PresenterConfiguration configuration) {
        super(view, configuration);
    }

    @Override
    public void openSpotifyLogin() {
        final AuthenticationRequest request = new AuthenticationRequest.Builder(CLIENT_ID,
                                                                                AuthenticationResponse.Type.TOKEN,
                                                                                REDIRECT_URI)
                .setScopes(new String[] { "user-read-private", "playlist-read", "playlist-read-private", "streaming" })
                .build();
        view.openLoginSpotifyLoginActivity(REQUEST_CODE, request);
    }

    @Override
    public void processSpotifyResponse(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
            switch (response.getType()) {
                // Response was successful and contains auth token
                case TOKEN:
                    String accessToken = response.getAccessToken();
                    //TODO:store token somewhere
                    checkUserInfo(accessToken);
                    break;

                // Auth flow returned an error
                case ERROR:
                    Timber.e("Auth error: " + response.getError());
                    break;

                // Most likely auth flow was cancelled
                default:
                    Timber.e("Auth result: " + response.getType());
            }
        }
    }

    private void checkUserInfo(String accessToken) {
        TokenStorage tokenStorage = new TokenStorage();
        tokenStorage.token = accessToken;
        SpotifyRetrofitClient.init(tokenStorage);
        Subscription subscription = spotifyApi.getCurrentUserProfile()
                .compose(subscribeOnIoObserveOnUi())
                .subscribe(user -> {
                    //Todo: check premium here
                }, RxUtils.logError());
        subscriptions.add(subscription);
    }
}
