package com.java.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PhraseFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(android.R.layout.simple_list_item_1,
				container, false);
		String frase = getArguments().getString("frase");
		TextView fraseView = (TextView) view.findViewById(android.R.id.text1);
		fraseView.setText(frase);
		fraseView.setTextColor(Color.BLACK);

		return view;
	}
}
