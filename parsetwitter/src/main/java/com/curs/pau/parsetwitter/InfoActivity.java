package com.curs.pau.parsetwitter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;
import java.util.Random;


public class InfoActivity extends ActionBarActivity {

    private TextView textView;
    private Button button;
    private Button publicButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        textView = (TextView) findViewById(R.id.text);
        button = (Button) findViewById(R.id.button);
        publicButton = (Button) findViewById(R.id.publicButton);

        fetchOnlineData();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser user = ParseUser.getCurrentUser();

                final ParseObject privateNote = new ParseObject("Note");
                Random random = new Random();

                privateNote.put("content", "This note is private! "
                        + random.nextInt(100) + " " + user.getUsername());
                privateNote.setACL(new ParseACL(ParseUser.getCurrentUser()));
                privateNote.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        textView.setText(privateNote.getString("content"));
                    }
                });
            }
        });

        publicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser user = ParseUser.getCurrentUser();

                final ParseObject publicNote = new ParseObject("Note");
                Random random = new Random();

                publicNote.put("content", "This note is public... "
                        + random.nextInt(100) + " " + user.getUsername());
                publicNote.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        textView.setText(publicNote.getString("content"));
                    }
                });
            }
        });


        publicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser user = ParseUser.getCurrentUser();

                final ParseObject publicNote = new ParseObject("Note");
                Random random = new Random();

                publicNote.put("content", "This note is public... "
                        + random.nextInt(100) + " " + user.getUsername());
                publicNote.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        textView.setText(publicNote.getString("content"));
                    }
                });
            }
        });
    }

    private void fetchOnlineData()  {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Note");
        try {
            List<ParseObject> data = query.find();
            String r = "";
            for(ParseObject o: data) {
                r += o.getString("content") + "\n";
            }
            textView.setText(r);
        } catch (ParseException e) {
            Toast.makeText(getApplicationContext(), "Something was wrong", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info, menu);
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
            fetchOnlineData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
