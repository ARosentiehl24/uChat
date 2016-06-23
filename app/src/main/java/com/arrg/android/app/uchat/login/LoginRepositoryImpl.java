package com.arrg.android.app.uchat.login;

import com.arrg.android.app.uchat.entities.User;
import com.arrg.android.app.uchat.domain.FirebaseHelper;
import com.arrg.android.app.uchat.lib.EventBus;
import com.arrg.android.app.uchat.lib.GreenRobotEventBus;
import com.arrg.android.app.uchat.login.events.LoginEvent;
import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class LoginRepositoryImpl implements LoginRepository {

    private Firebase dataReference;
    private Firebase myUserReference;
    private FirebaseHelper firebaseHelper;

    public LoginRepositoryImpl() {
        this.dataReference = firebaseHelper.getDataReference();
        this.myUserReference = firebaseHelper.getMyUserReference();
        this.firebaseHelper = FirebaseHelper.getInstance();
    }

    @Override
    public void Login(String email, String password) {
        postEvent(LoginEvent.onLoginSuccess);
    }

    @Override
    public void SignIn(String email, String password) {
        dataReference.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                myUserReference = firebaseHelper.getMyUserReference();
                myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User currentUser = dataSnapshot.getValue(User.class);

                        if (currentUser == null) {
                            String email = firebaseHelper.getAuthUserEmail();

                            if (email != null) {
                                currentUser = new User();
                                myUserReference.setValue(currentUser);
                            }
                        }

                        firebaseHelper.changeUserConnectionStatus(User.ONLINE);

                        postEvent(LoginEvent.onSignInSuccess);
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                postEvent(LoginEvent.onSignInError, firebaseError.getMessage());
            }
        });
    }

    @Override
    public void checkSession() {
        postEvent(LoginEvent.onFailedToRecoverSession);
    }

    private void postEvent(int type, String message) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);

        if (message != null) {
            loginEvent.setErrorMessage(message);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);
    }

    private void postEvent(int type) {
        postEvent(type, null);
    }
}
