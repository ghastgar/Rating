package com.curs.pau.drawer.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.curs.pau.drawer.R;

/**
 * Created by pau on 29/01/15.
 */
public class BasicFragment extends Fragment {
    // li hem de dir com pintar les coses
    // hem afegit l'esquelet del que ve ara
    // fent click dret -> Generate -> Override Methods
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_basic, container, false);
    }
}
