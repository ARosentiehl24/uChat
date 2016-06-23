package com.arrg.android.app.uchat.login;

import com.arrg.android.app.uchat.login.events.LoginEvent;

public interface LoginPresenter {
    void onCreate();

    void onDestroy();

    void checkForAuthenticated();

    void validateLogin(String email, String password);

    void registerNewUser(String email, String password);

    void onEventMainThread(LoginEvent event);
}
