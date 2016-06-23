package com.arrg.android.app.uchat.login;

public interface LoginInteractor {
    void checkSession();

    void doLogin(String email, String password);

    void SignIn(String email, String password);
}
