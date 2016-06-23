package com.arrg.android.app.uchat.login.events;

public class LoginEvent {

    public static final int onLoginError = 0;
    public static final int onSignInError = 1;
    public static final int onLoginSuccess = 2;
    public static final int onSignInSuccess = 3;
    public static final int onFailedToRecoverSession = 4;

    private int eventType;
    private String errorMessage;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
