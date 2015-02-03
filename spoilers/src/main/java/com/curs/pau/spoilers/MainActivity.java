package com.curs.pau.spoilers;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.w3c.dom.Text;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    private TextView spoiler;
    private Button meh;
    private Button buah;
    private List<ParseObject> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spoiler = (TextView) findViewById(R.id.spoiler);
        meh = (Button) findViewById(R.id.meh);
        buah = (Button) findViewById(R.id.buah);

        if (savedInstanceState == null) initParse();
        fetchOnlineData();

        meh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchOnlineData();
            }
        });

        buah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchOnlineData();
            }
        });

    }

    private void nextSpoiler(TextView spoiler) {
        if (data.iterator().hasNext()) spoiler.setText(data.iterator().next().getString("text"));
        else spoiler.setText("You read all the spoilers available!");
    }

    private void initParse() {
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "zJB0oZnkQLDjJNOvenpD8nVWSsdz8eIlmT3vhfGX", "lnkPk6JAaQTHebPgDZkTEFZkit97oUPWj7ymgR19");
    }

    private void fetchOnlineData()  {
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Spoiler");
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (object != null) {
                    spoiler.setText(object.getString("text"));
                    query.setSkip(1);
                } else {
                    Log.d("score", "Fail retrieving the object.");
                    Toast.makeText(getApplicationContext(), "Something was wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
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