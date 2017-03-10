package io.intrepid.meddle.screens.login;

import android.support.annotation.NonNull;

import io.intrepid.meddle.base.BasePresenter;
import io.intrepid.meddle.base.PresenterConfiguration;
import io.intrepid.meddle.models.UserModel;
import io.intrepid.meddle.utils.RxUtils;
import rx.Subscription;

class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    LoginPresenter(@NonNull LoginContract.View view,
                   @NonNull PresenterConfiguration configuration) {
        super(view, configuration);
    }

    @Override
    public void onLoginButtonClicked(String userName) {
        UserModel userModel = new UserModel();
        userModel.displayName = userName;
        Subscription subscription = restApi.saveUser(userModel)
                .compose(subscribeOnIoObserveOnUi())
                .subscribe(user -> {
                    view.goToProvidersActivity();
                }, RxUtils.logError());
        subscriptions.add(subscription);
    }
}
