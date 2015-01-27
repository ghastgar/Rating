package com.curs.pau.adapters;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ListView mListView;
    private ArrayList products;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listView);

        products = new ArrayList<>();
        products.add("Moritz");
        products.add("Damm");
        products.add("Asahi");
        products.add("Kirin");
        products.add("080");
        products.add("Coronita");
        products.add("Montseny");
        products.add("Moska Negra");
        products.add("Almogavers");
        products.add("Fantome Saison");
        products.add("Moritz");
        products.add("Damm");
        products.add("Asahi");
        products.add("Kirin");
        products.add("080");
        products.add("Coronita");
        products.add("Montseny");
        products.add("Moska Negra");
        products.add("Almogavers");
        products.add("Fantome Saison");

        adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.custom_item, R.id.text1, products);

        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), position + ": " + products.get(position), Toast.LENGTH_SHORT).show();
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
            //products.add("NOU PRODUCTE");
            adapter.add("NOU PRODUCTE");
            Toast.makeText(getApplicationContext(), "afegit", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
