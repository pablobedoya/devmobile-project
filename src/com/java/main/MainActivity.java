package com.java.main;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

		leftDrawer = (ListView) findViewById(R.id.left_drawer);
		listItems = new String[] { "Primeiro item", "Segundo item" };
		leftDrawer.setAdapter(new ArrayAdapter<String>(MainActivity.this,
				R.layout.item_menu, listItems));

		leftDrawer.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> container, View view,
					int index, long id) {
				navigationDrawer.closeDrawer(leftDrawer);
				MainActivity.this.getSupportActionBar().setTitle(
						listItems[index]);

				Bundle args = new Bundle();
				args.putString("frase", listItems[index]);

				Fragment fraseFragmento = new PhraseFragment();
				fraseFragmento.setArguments(args);

				FragmentManager gerenciadorFragmentos = getSupportFragmentManager();
				gerenciadorFragmentos.beginTransaction()
						.replace(R.id.content_frame, fraseFragmento).commit();
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
