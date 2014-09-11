package com.java.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StateArrayAdapter extends ArrayAdapter<State> {
	private static final String tag = "StateArrayAdapter";
	private static final String ASSETS_DIR = "images/";
	private Context context;

	private ImageView stateIcon;
	private TextView stateName;
	private TextView text;
	private List<State> states = new ArrayList<State>();

	public StateArrayAdapter(Context context, int textViewResourceId,
			List<State> objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.states = objects;
	}

	public int getCount() {
		return this.states.size();
	}

	public State getItem(int index) {
		return this.states.get(index);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			// ROW INFLATION
			Log.d(tag, "Starting XML Row Inflation...");
			LayoutInflater inflater = (LayoutInflater) this.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.state_list_item, parent, false);
			Log.d(tag, "Successfully completed XML Row Inflation!");
		}

		// Get item
		State state = getItem(position);
		stateIcon = (ImageView) row.findViewById(R.id.state_icon);
		stateName = (TextView) row.findViewById(R.id.state_name);
		text = (TextView) row.findViewById(R.id.text);

		stateName.setText(state.getName());
		String imgFilePath = ASSETS_DIR + state.resourceId;
		try {
			Bitmap bitmap = BitmapFactory.decodeStream(this.context
					.getResources().getAssets().open(imgFilePath));
			stateIcon.setImageBitmap(bitmap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		text.setText("");

		return row;
	}
}
