package com.arrg.android.app.uchat.login;

public interface LoginRepository {
    void Login(String email, String password);

    void SignIn(String email, String password);

    void checkSession();
}
