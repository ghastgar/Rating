package com.curs.pau.rating;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private TextView mTextView;
    private EditText mEditTextMarca;
    private EditText mEditTextModel;
    private Button mButton;
    private Button mXButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView2);
        mEditTextMarca = (EditText) findViewById(R.id.editTextMarca);
        mEditTextModel = (EditText) findViewById(R.id.editTextModel);
        mButton = (Button) findViewById(R.id.button);
        mXButton = (Button) findViewById(R.id.xButton);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // com a 1r parametre de vegades es posa this pero es millor agafar directament
                // el context actual amb aquest metode
                // el 2n parametre indica a quina activity "volem anar"
                Intent mIntent = new Intent(getApplicationContext(), SecondActivity.class);
                if (mEditTextModel.getText().toString().equals("") ||
                        mEditTextMarca.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Introdueix marca i model", Toast.LENGTH_SHORT).show();
                    // -> toast necessita el context per saber on mostrar el missatge
                }
                else {
                    mIntent.putExtra("model", mEditTextModel.getText().toString());  // el 1r param
                    mIntent.putExtra("marca", mEditTextMarca.getText().toString());  // es una etiqueta
                    startActivity(mIntent);  // "muta" a l'altra activity
                }
            }
        });

        mXButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditTextMarca.setText("");
                mEditTextModel.setText("");
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
