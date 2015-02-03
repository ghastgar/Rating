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

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;


public class MainActivity extends ActionBarActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        // prevent crash if screen orientation changes
        if (savedInstanceState == null) initParse();

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
            startActivity(intent);
        }
    }

    private void initComponents() {
        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.login);
        signupButton = (Button) findViewById(R.id.signup);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = usernameEditText.getText().toString();
                String pass = passwordEditText.getText().toString();
                if (name.isEmpty() || pass.isEmpty())
                    Toast.makeText(
                            getApplicationContext(),
                            "Enter username and password or Sign Up",
                            Toast.LENGTH_SHORT).show();
                else {
                    ParseUser.logInInBackground(name, pass, new LogInCallback() {
                        public void done(ParseUser user, ParseException e) {
                            if (user != null) {
                                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                                // Hooray! The user is logged in.
                            } else {
                                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
                                // Signup failed. Look at the ParseException to see what happened.
                            }
                        }
                    });
                }
            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(), SignupActivity.class);
                mIntent.putExtra("username", usernameEditText.getText().toString());
                startActivity(mIntent);  // "muta" a l'altra activity
            }
        });
    }

    private void initParse() {
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "zJB0oZnkQLDjJNOvenpD8nVWSsdz8eIlmT3vhfGX", "lnkPk6JAaQTHebPgDZkTEFZkit97oUPWj7ymgR19");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
