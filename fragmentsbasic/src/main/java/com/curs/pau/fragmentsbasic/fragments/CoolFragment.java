package com.curs.pau.fragmentsbasic.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.curs.pau.fragmentsbasic.R;

/**
 * Created by pau on 29/01/15.
 */
public class CoolFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cool, container, false);
    }
}
