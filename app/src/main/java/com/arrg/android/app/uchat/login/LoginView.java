package com.arrg.android.app.uchat.login;

public interface LoginView {
    void enableInputs();

    void disableInputs();

    void showProgress();

    void hideProgress();

    void handleLogin();

    void handleSignIn();

    void navigateToMainScreen();

    void loginError(String error);

    void newUserSuccess();

    void newUserError(String error);
}
