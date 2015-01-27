package com.curs.pau.customadapter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.curs.pau.customadapter.adapter.MyCustomAdapter;
import com.curs.pau.customadapter.model.Cervesa;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private ListView mListView;
    private EditText mEditText;
    private Button mButton;
    private ArrayList<Cervesa> cerveses = new ArrayList<>();
    private MyCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);
        mEditText = (EditText) findViewById(R.id.editText);
        mButton = (Button) findViewById(R.id.button);
        adapter = new MyCustomAdapter(getApplicationContext(), cerveses);

        fetchData();

        mListView.setAdapter(adapter);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mEditText.getText().toString().equals("")) {
                    String text = mEditText.getText().toString();
                    String rating = "Default";
                    Cervesa bier = new Cervesa(text, rating);
                    adapter.add(bier);
                    mEditText.setText("");
                }
            }
        });
    }

    private void fetchData() {
        cerveses.add(new Cervesa("Estrella", "Low"));
        cerveses.add(new Cervesa("A.K.", "High"));
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
