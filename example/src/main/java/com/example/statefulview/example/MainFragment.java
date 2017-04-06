package com.example.statefulview.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import uk.co.jamiecruwys.State;
import uk.co.jamiecruwys.StatefulFragment;

/**
 * Created by Jamie Cruwys of 3 SIDED CUBE on 06/04/2017.
 */
public class MainFragment extends StatefulFragment
{
	@Override protected int provideContentLayout()
	{
		return R.layout.activity_main;
	}

	@Override protected int provideEmptyLayout()
	{
		return R.layout.empty;
	}

	@Override protected int provideLoadingLayout()
	{
		return R.layout.loading;
	}

	@Override protected int provideErrorLayout()
	{
		return R.layout.error;
	}

	@Override protected int provideOfflineLayout()
	{
		return R.layout.offline;
	}

	@Override public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		inflater.inflate(R.menu.state_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.menu_content:
				setState(State.SHOWING_CONTENT);
				break;

			case R.id.menu_empty:
				setState(State.EMPTY);
				break;

			case R.id.menu_loading:
				setState(State.LOADING);
				break;

			case R.id.menu_error:
				setState(State.ERROR);
				break;

			case R.id.menu_offline:
				setState(State.OFFLINE);
				break;
		}

		return true;
	}
}