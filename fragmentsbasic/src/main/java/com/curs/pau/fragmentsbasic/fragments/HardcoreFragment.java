package com.curs.pau.fragmentsbasic.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.curs.pau.fragmentsbasic.R;

/**
 * Created by pau on 29/01/15.
 */
public class HardcoreFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        Button button1 = (Button) view.findViewById(R.id.button1);
        Button button2 = (Button) view.findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().
                        beginTransaction().
                        replace(R.id.content_fragment, new BasicFragment())
                        .commit();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().
                        beginTransaction().
                        replace(R.id.content_fragment, new CoolFragment())
                        .commit();
            }
        });

        return view;
    }
}
