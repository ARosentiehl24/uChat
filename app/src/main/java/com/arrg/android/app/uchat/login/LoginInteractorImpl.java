package com.arrg.android.app.uchat.login;

public class LoginInteractorImpl implements LoginInteractor {

    private LoginRepository loginRepository;

    public LoginInteractorImpl() {
        this.loginRepository = new LoginRepositoryImpl();
    }

    @Override
    public void checkSession() {
        loginRepository.checkSession();
    }

    @Override
    public void doLogin(String email, String password) {
        loginRepository.Login(email, password);
    }

    @Override
    public void SignIn(String email, String password) {
        loginRepository.SignIn(email, password);
    }
}
