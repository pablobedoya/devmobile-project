package com.java.main;

import java.io.InputStream;
import java.util.List;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ActionBarActivity {
	private String[] listItems;
	private ListView leftDrawer;
	private DrawerLayout navigationDrawer;
	private ActionBarDrawerToggle selectorActionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Create Parser for raw/states.xml
		StateParser stateParser = new StateParser();
		InputStream inputStream = getResources().openRawResource(
				R.raw.states);

		// Parse the inputstream
		stateParser.parse(inputStream);
		
		// Get states
		List<State> stateList = stateParser.getList();
		
		// Create a customized ArrayAdapter
		StateArrayAdapter adapter = new StateArrayAdapter(
				getApplicationContext(), R.layout.state_list_item, stateList);
		
		leftDrawer = (ListView) findViewById(R.id.left_drawer);
		leftDrawer.setAdapter(adapter);
		leftDrawer.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> container, View view,
					int index, long id) {
				navigationDrawer.closeDrawer(leftDrawer);
				MainActivity.this.getSupportActionBar().setTitle(
						listItems[index]);

				Bundle args = new Bundle();
				args.putString("frase", listItems[index]);

				Fragment fragment = new PhraseFragment();
				fragment.setArguments(args);

				FragmentManager fragmentManager = getSupportFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager
						.beginTransaction();
				fragmentTransaction.replace(R.id.content_frame, fragment);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
			}
		});

		navigationDrawer = (DrawerLayout) findViewById(R.id.navigation_drawer);
		navigationDrawer.setDrawerShadow(R.drawable.shadow_drawer,
				GravityCompat.START);

		selectorActionBar = new ActionBarDrawerToggle(MainActivity.this,
				navigationDrawer, R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				MainActivity.this.getSupportActionBar().setTitle(
						R.string.app_name);
			}
		};
		navigationDrawer.setDrawerListener(selectorActionBar);

		getSupportActionBar().setTitle(R.string.app_name);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (selectorActionBar.onOptionsItemSelected(item))
			return true;
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		selectorActionBar.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		selectorActionBar.onConfigurationChanged(newConfig);
	}
}
