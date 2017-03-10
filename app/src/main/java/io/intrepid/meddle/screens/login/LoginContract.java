package io.intrepid.meddle.screens.login;

import io.intrepid.meddle.base.BaseContract;

class LoginContract {
    public interface View extends BaseContract.View {
        void goToProvidersActivity();
    }

    public interface Presenter extends BaseContract.Presenter<LoginContract.View> {
        void onLoginButtonClicked(String userName);
    }
}
