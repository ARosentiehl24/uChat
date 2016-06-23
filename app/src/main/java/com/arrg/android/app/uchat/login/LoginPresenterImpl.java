package com.arrg.android.app.uchat.login;

import android.util.Log;

import com.arrg.android.app.uchat.lib.EventBus;
import com.arrg.android.app.uchat.lib.GreenRobotEventBus;
import com.arrg.android.app.uchat.login.events.LoginEvent;

public class LoginPresenterImpl implements LoginPresenter {

    private EventBus eventBus;
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        loginView = null;
        eventBus.unregister(this);
    }

    @Override
    public void checkForAuthenticated() {
        if (loginView != null) {
            loginView.disableInputs();
            loginView.showProgress();
        }

        loginInteractor.checkSession();
    }

    @Override
    public void validateLogin(String email, String password) {
        if (loginView != null) {
            loginView.disableInputs();
            loginView.showProgress();
        }

        loginInteractor.doLogin(email, password);
    }

    @Override
    public void registerNewUser(String email, String password) {
        if (loginView != null) {
            loginView.disableInputs();
            loginView.showProgress();
        }

        loginInteractor.SignIn(email, password);
    }

    @Override
    public void onEventMainThread(LoginEvent event) {
        switch (event.getEventType()) {
            case LoginEvent.onLoginSuccess:
                onLoginSuccess();
                break;
            case LoginEvent.onSignInSuccess:
                onSignInSuccess();
                break;
            case LoginEvent.onLoginError:
                onLoginError(event.getErrorMessage());
                break;
            case LoginEvent.onSignInError:
                onSignInError(event.getErrorMessage());
                break;
            case LoginEvent.onFailedToRecoverSession:
                onFailedToRecoverSession();
                break;
        }
    }

    private void onFailedToRecoverSession() {
        if (loginView != null) {
            loginView.enableInputs();
            loginView.hideProgress();
        }

        Log.e("LoginPresenterImpl", "onFailedToRecoverSession");
    }

    private void onLoginSuccess() {
        if (loginView != null) {
            loginView.navigateToMainScreen();
        }
    }

    private void onSignInSuccess() {
        if (loginView != null) {
            loginView.newUserSuccess();
        }
    }

    private void onLoginError(String error) {
        if (loginView != null) {
            loginView.enableInputs();
            loginView.hideProgress();
            loginView.loginError(error);
        }
    }

    private void onSignInError(String error) {
        if (loginView != null) {
            loginView.enableInputs();
            loginView.hideProgress();
            loginView.newUserError(error);
        }
    }
}
