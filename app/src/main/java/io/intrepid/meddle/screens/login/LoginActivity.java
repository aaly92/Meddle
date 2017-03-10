package io.intrepid.meddle.screens.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import io.intrepid.meddle.R;
import io.intrepid.meddle.base.BaseMvpActivity;
import io.intrepid.meddle.base.PresenterConfiguration;
import io.intrepid.meddle.screens.providers.ProvidersActivity;

public class LoginActivity extends BaseMvpActivity<LoginContract.Presenter> implements LoginContract.View {
    @BindView(R.id.username)
    EditText userName;

    @NonNull
    @Override
    public LoginContract.Presenter createPresenter(PresenterConfiguration configuration) {
        return new LoginPresenter(this, configuration);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.login_button)
    void onLoginButtonClicked() {
        presenter.onLoginButtonClicked(userName.getText().toString());
    }


    @Override
    public void goToProvidersActivity() {
        Intent intent = new Intent(this, ProvidersActivity.class);
        startActivity(intent);
    }
}
