package br.com.slim.venda.teste;

import org.holoeverywhere.LayoutInflater;
import org.holoeverywhere.app.Fragment;
import org.holoeverywhere.widget.TextView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

public class TestFragment extends Fragment {

public static final String EXTRA_TITLE = "title";

public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
    TextView txt = new TextView(inflater.getContext());
    txt.setGravity(Gravity.CENTER);
    txt.setText("Fragment");

    if (getArguments() != null && getArguments().containsKey(EXTRA_TITLE)) {
        txt.setText(getArguments().getString(EXTRA_TITLE));
    }
    return txt;
}

public static Bundle createBundle(String title) {
    Bundle bundle = new Bundle();
    bundle.putString(EXTRA_TITLE, title);
    return bundle;
}
}