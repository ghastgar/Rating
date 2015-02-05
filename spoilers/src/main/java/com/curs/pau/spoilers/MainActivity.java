package com.curs.pau.spoilers;

import android.content.Intent;
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
    private Integer k;
    private Integer maxK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spoiler = (TextView) findViewById(R.id.spoiler);
        meh = (Button) findViewById(R.id.meh);
        buah = (Button) findViewById(R.id.buah);
        k = 0;

        if (savedInstanceState == null) initParse();

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

    private void initParse() {
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "zJB0oZnkQLDjJNOvenpD8nVWSsdz8eIlmT3vhfGX", "lnkPk6JAaQTHebPgDZkTEFZkit97oUPWj7ymgR19");
    }

    private void fetchOnlineData()  {
        if (k != -1) {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Spoilers");
            query.setSkip(k);
            query.getFirstInBackground(new GetCallback<ParseObject>() {
                public void done(ParseObject object, ParseException e) {
                    if (object != null) {
                        spoiler.setText(object.getString("text"));
                        ++k;
                        //Toast.makeText(getApplicationContext(), "object != null", Toast.LENGTH_SHORT).show();
                    } else {
                        maxK = k-1;
                        k = -1;
                        spoiler.setText("Ja has llegit tots els spoilers disponibles. Afegeix-ne de nous!");
                        Log.d("score", "Fail retrieving the object.");
                        //Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
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
        if (id == R.id.add) {
            k = maxK;
            Intent intent = new Intent(getApplicationContext(), AddActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
