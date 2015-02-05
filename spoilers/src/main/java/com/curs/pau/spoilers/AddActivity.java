package com.curs.pau.spoilers;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;

/**
 * Created by pau on 05/02/15.
 */
public class AddActivity extends Activity {
    private EditText editText;
    private Button add;
    private AddActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editText = (EditText) findViewById(R.id.editText);
        add = (Button) findViewById(R.id.add);
        activity = this;

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                if (text == "") {
                    Toast.makeText(getApplicationContext(), "Is this even a spoiler?", Toast.LENGTH_SHORT).show();
                }
                else {
                    ParseObject parseObject = new ParseObject("Spoilers");
                    parseObject.put("text", text);
                    parseObject.saveInBackground();
                    editText.setText("");
                    try {
                        Toast.makeText(getApplicationContext(), "Spoiler added!", Toast.LENGTH_SHORT).show();
                        activity.finish();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }
            }
        });
    }
}
