package com.arrg.android.app.uchat.login;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arrg.android.app.uchat.R;
import com.arrg.android.app.uchat.contactlist.ContactListActivity;

import net.colindodd.gradientlayout.GradientLinearLayout;

import org.fingerlinks.mobile.android.navigator.Navigator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @Bind(R.id.email)
    AppCompatEditText email;
    @Bind(R.id.password)
    AppCompatEditText password;
    @Bind(R.id.showHidePassword)
    AppCompatCheckBox showHidePassword;
    @Bind(R.id.btnLogin)
    AppCompatButton btnLogin;
    @Bind(R.id.btnSignIn)
    AppCompatButton btnSignIn;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.container)
    GradientLinearLayout container;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenterImpl(this);
        loginPresenter.onCreate();
        loginPresenter.checkForAuthenticated();
    }

    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();
    }

    @OnClick({R.id.btnLogin, R.id.btnSignIn})
    public void onClick(View view) {
        if (isEmpty(email) || isEmpty(password)) {
            Toast.makeText(this, R.string.empty_fields_message, Toast.LENGTH_SHORT).show();
        } else {
            switch (view.getId()) {
                case R.id.btnLogin:
                    handleLogin();
                    break;
                case R.id.btnSignIn:
                    handleSignIn();
                    break;
            }
        }
    }

    @OnCheckedChanged(R.id.showHidePassword)
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        password.setSelection(password.length());
    }

    public boolean isEmpty(AppCompatEditText appCompatEditText) {
        return appCompatEditText.getText().length() != 0;
    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void handleLogin() {
        loginPresenter.validateLogin(email.getText().toString(), password.getText().toString());
    }

    @Override
    public void handleSignIn() {
        loginPresenter.registerNewUser(email.getText().toString(), password.getText().toString());
    }

    @Override
    public void navigateToMainScreen() {
        Navigator.with(this).build().goTo(ContactListActivity.class).animation().commit();
    }

    @Override
    public void loginError(String error) {
        password.setText("");

        String message = String.format(getString(R.string.login_error_message), error);

        password.setError(message);
    }

    @Override
    public void newUserSuccess() {
        Snackbar.make(container, R.string.sign_in_success_message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void newUserError(String error) {
        password.setText("");

        String message = String.format(getString(R.string.sign_in_error_message), error);

        password.setError(message);
    }

    private void setInputs(boolean enabled) {
        email.setEnabled(enabled);
        password.setEnabled(enabled);
        showHidePassword.setEnabled(enabled);
        btnLogin.setEnabled(enabled);
        btnSignIn.setEnabled(enabled);
    }
}
