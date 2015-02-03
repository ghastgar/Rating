package com.curs.pau.parse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.DeleteCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ListView listView;
    private ArrayList<String> animes = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private EditText editText;
    private Button okButton;
    private List<ParseObject> onlineData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parseSetup();
        listView = (ListView) findViewById(R.id.listView);
        animes = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, animes);
        editText = (EditText) findViewById(R.id.editText);
        okButton = (Button) findViewById(R.id.okButton);

        /*
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("restaurant", "voramar");
        testObject.saveInBackground();
        */

        fetchData();

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), position + ": " + animes.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                // 1. Instantiate an AlertDialog.Builder with its constructor
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                // 2. Chain together various setter methods to set the dialog characteristics
                builder.setMessage(R.string.dialog_message)
                        .setTitle(R.string.dialog_title);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ParseObject toDelete = onlineData.get(position);
                        toDelete.deleteInBackground(new DeleteCallback() {
                            @Override
                            public void done(ParseException e) {
                                onlineData.remove(position);
                                animes.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                // 3. Get the AlertDialog from create()
                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String text = editText.getText().toString();
                if (text.equals(""))
                    Toast.makeText(getApplicationContext(), "Introdueix una serie", Toast.LENGTH_SHORT).show();
                else {
                    final ParseObject novaSerie = new ParseObject("TestObject");
                    novaSerie.put("nomSerie", text);
                    novaSerie.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            adapter.add(text);
                            Toast.makeText(getApplicationContext(), text + " introduida!", Toast.LENGTH_SHORT).show();
                            editText.setText("");
                            onlineData.add(novaSerie);
                        }
                    });
                }
            }
        });
    }

    private void parseSetup() {
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "zJB0oZnkQLDjJNOvenpD8nVWSsdz8eIlmT3vhfGX", "lnkPk6JAaQTHebPgDZkTEFZkit97oUPWj7ymgR19");
    }

    private void fetchData() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("TestObject");
        try {
            onlineData = query.find();
            for (ParseObject object : onlineData) {
                String nom = object.getString("nomSerie");
                if (nom != null) animes.add(nom);
            }

        } catch (ParseException e) {
            e.printStackTrace();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
