package com.curs.pau.parsetwitter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class SignupActivity extends ActionBarActivity {
    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText passwordCheckEditText;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initComponents();
    }

    private void initComponents() {
        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);
        passwordCheckEditText = (EditText) findViewById(R.id.passwordCheck);
        emailEditText = (EditText) findViewById(R.id.email);
        signupButton = (Button) findViewById(R.id.signup);

        usernameEditText.setText(getIntent().getExtras().getString("username"));

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String pass = passwordEditText.getText().toString();
                String passCheck = passwordCheckEditText.getText().toString();

                if (username.isEmpty()) Toast.makeText(getApplicationContext(), "Enter username", Toast.LENGTH_SHORT).show();
                else if (email.isEmpty()) Toast.makeText(getApplicationContext(), "Enter email", Toast.LENGTH_SHORT).show();
                else if (pass.isEmpty()) Toast.makeText(getApplicationContext(), "Enter password", Toast.LENGTH_SHORT).show();
                else if (!pass.equals(passCheck)) Toast.makeText(getApplicationContext(), "Passwords don't match", Toast.LENGTH_SHORT).show();
                else {
                    ParseUser user = new ParseUser();
                    user.setUsername(username);
                    user.setPassword(pass);
                    user.setEmail(email);

                    user.signUpInBackground(new SignUpCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_SHORT).show();
                                usernameEditText.setText("");
                                passwordEditText.setText("");
                                passwordCheckEditText.setText("");
                                emailEditText.setText("");
                                signupButton.setText("");
                                // Hooray! Let them use the app now.
                            } else {
                                // Sign up didn't succeed. Look at the ParseException
                                // to figure out what went wrong
                                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
