package com.arrg.android.app.uchat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.email)
    AppCompatEditText email;
    @Bind(R.id.password)
    AppCompatEditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnLogin, R.id.btnSignIn})
    public void onClick(View view) {
        if (isEmpty(email) || isEmpty(password)) {
            Toast.makeText(this, R.string.empty_fields_message, Toast.LENGTH_SHORT).show();
        } else {
            switch (view.getId()) {
                case R.id.btnLogin:
                    break;
                case R.id.btnSignIn:
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
}
