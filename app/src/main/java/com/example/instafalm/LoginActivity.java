package com.example.instafalm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    public EditText etUsername;
    public EditText etPassword;
    public Button btnLogin;
    public Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //IF the exception is null, then the login was successful
        if(ParseUser.getCurrentUser() != null){
            goMainActivtiy();
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username,password);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick signUp button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                signUpUser(username,password);
            }
        });
    }

    private void signUpUser(final String username, final String password){
        Log.i(TAG, "Attempting to register user" + etUsername);
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {

                
                if(username.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Username Field Required!", Toast.LENGTH_LONG).show();
                    return;
                }
                else if(password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Password Field Required!", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    if (e != null) {
                        Log.e(TAG, "Issue with signUp", e);
                        Toast.makeText(LoginActivity.this, "User Already Exists", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                goMainActivtiy();
                Toast.makeText(LoginActivity.this, "Successful Login!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loginUser(final String username, final String password){
        Log.i(TAG, "Attempting to login user" + etPassword);
        //The following code uses parse to login the user

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            //If the login succeseds then parse makes the ParseException e null, so check that
            public void done(ParseUser user, ParseException e) {
                if(e != null){
                    if(username.isEmpty()){
                        Toast.makeText(LoginActivity.this, "Username Field Required!", Toast.LENGTH_LONG).show();
                        return;
                    }
                    else if(password.isEmpty()){
                        Toast.makeText(LoginActivity.this, "Password Field Required!", Toast.LENGTH_LONG).show();
                        return;
                    }
                    else {
                        Log.e(TAG, "Issue with login", e);
                        Toast.makeText(LoginActivity.this, "Incorrect Username or Password!", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                goMainActivtiy();
                Toast.makeText(LoginActivity.this, "Successful Login!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void goMainActivtiy() {
        //Make a new intent that on a succesful login takes the user back to MainActivtiy
            //Intents need to know where coming from and where to go
        Intent i =  new Intent(this, MainActivity.class);
        startActivity(i);
    }
}