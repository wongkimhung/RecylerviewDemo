package com.rtfsc.recylerviewdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.rtfsc.recylerviewdemo.R;
import com.rtfsc.recylerviewdemo.view.fragment.RecyclerGridFragment;
import com.rtfsc.recylerviewdemo.view.fragment.RecyclerListFragment;

public class MainActivity extends AppCompatActivity implements MainFragment.OnListItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		});


		if (savedInstanceState == null) {
			MainFragment mainFragment = new MainFragment();
			getSupportFragmentManager().beginTransaction()
					.add(R.id.content, mainFragment)
					.commit();
		}
	}

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

	@Override
	public void onListItemClick(int position) {
		Fragment fragment = null;
		switch (position) {
			case 0:
				//当点击第一个item时候，new一个RecyclerListFragment
				fragment = new RecyclerListFragment();
				break;
			case 1:
				//当点击第二个item时候，new一个RecyclerGridFragment
				fragment = new RecyclerGridFragment();
				break;
		}
		//这次用replace，替换framelayout的布局，也就是MainFragment
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content, fragment)
				.addToBackStack(null)
				.commit();
	}
}
