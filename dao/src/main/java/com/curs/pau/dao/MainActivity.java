package com.curs.pau.dao;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.Random;


public class MainActivity extends OrmLiteBaseActivity<RestaurantORMHelper> {

    Button llegirBD;
    Button desarBD;
    TextView llistat;
    EditText nom;
    EditText rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llegirBD = (Button) findViewById(R.id.llegir);
        desarBD = (Button) findViewById(R.id.desar);
        llistat = (TextView) findViewById(R.id.llistat);
        nom = (EditText) findViewById(R.id.nomRest);
        rate = (EditText) findViewById(R.id.rate);

        // listener pel boto de guardar dades
        desarBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int numeroRandom = random.nextInt(100);
                String nomRest = "aaa" + numeroRandom + "";  // + "" es igual a fer .toString()
                String tipus = "fast";
                numeroRandom = random.nextInt(10);
                int valoracio = numeroRandom;

                nomRest = nom.getText().toString();

                valoracio = Integer.parseInt(rate.getText().toString());


                RestaurantORMDao restaurantORMDao = new RestaurantORMDao(nomRest, valoracio, tipus);
                try {
                    Dao<RestaurantORMDao, Integer> dao = getHelper().getDao();
                    dao.create(restaurantORMDao);
                    llistat.setText("Restaurant: " + nomRest + " ben inserit");
                } catch (SQLException e) {
                    llistat.setText("Error a l'inserir: " + nomRest);
                    e.printStackTrace();
                }
            }
        });

        llegirBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 11; ++i) {
                    Random random = new Random();
                    int numeroRandom = random.nextInt(100);
                    int notaRandom = random.nextInt(10);
                    String noob = "aaa" + numeroRandom + " " + notaRandom + " fast\n";
                    llistat.append(noob);
                }
            }
        });
    }

    /* En altres versions d'Android no servia fer-ho a l'onCreate
    @Override
    protected void onStart() {
        super.onStart();
        llegirBD = (Button) findViewById(R.id.llegir);
    }*/

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
