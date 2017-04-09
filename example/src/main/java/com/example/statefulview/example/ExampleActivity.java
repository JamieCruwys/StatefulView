package com.example.statefulview.example;

import android.view.Menu;
import android.view.MenuItem;

import uk.co.jamiecruwys.StatefulActivity;
import uk.co.jamiecruwys.ViewState;

/**
 * Created by user on 09/04/2017.
 */

public class ExampleActivity extends StatefulActivity
{
	@Override public int provideLoadingLayout()
	{
		return R.layout.loading;
	}

	@Override public int provideEmptyLayout()
	{
		return R.layout.empty;
	}

	@Override public int provideLoadedLayout()
	{
		return R.layout.loaded;
	}

	@Override public int provideErrorLayout()
	{
		return R.layout.error;
	}

	@Override public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.state_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.menu_loading:
				setViewState(ViewState.LOADING);
				break;

			case R.id.menu_empty:
				setViewState(ViewState.EMPTY);
				break;

			case R.id.menu_loaded:
				setViewState(ViewState.LOADED);
				break;

			case R.id.menu_error:
				setViewState(ViewState.ERROR);
				break;
		}

		return true;
	}
}