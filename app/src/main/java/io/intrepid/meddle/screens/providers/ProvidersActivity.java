package io.intrepid.meddle.screens.providers;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;

import butterknife.OnClick;
import io.intrepid.meddle.R;
import io.intrepid.meddle.base.BaseMvpActivity;
import io.intrepid.meddle.base.PresenterConfiguration;
import io.intrepid.meddle.screens.example1.Example1Activity;
import io.intrepid.meddle.screens.login.LoginActivity;

public class ProvidersActivity extends BaseMvpActivity<ProvidersContract.Presenter> implements ProvidersContract.View {
    @NonNull
    @Override
    public ProvidersContract.Presenter createPresenter(PresenterConfiguration configuration) {
        return new ProvidersPresenter(this, configuration);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_providers;
    }

    @OnClick(R.id.spotify)
    void onSpotifyButtonClicked() {
        presenter.openSpotifyLogin();
    }

    @Override
    public void openLoginSpotifyLoginActivity(int requestCode, AuthenticationRequest request) {
        AuthenticationClient.openLoginActivity(this, requestCode, request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        presenter.processSpotifyResponse(requestCode, resultCode, intent);
    }

    @Override
    public void launchNextActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void launchNotPremiumAccountScreen() {
        Intent intent = new Intent(this, Example1Activity.class);
        startActivity(intent);
    }
}
