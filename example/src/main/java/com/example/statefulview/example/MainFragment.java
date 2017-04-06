package com.example.statefulview.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
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

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = super.onCreateView(inflater, container, savedInstanceState);
		ButterKnife.bind(this, view);
		return view;
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
				onShowingContentState();
				break;

			case R.id.menu_empty:
				onEmptyState();
				break;

			case R.id.menu_loading:
				onLoadingState();
				break;

			case R.id.menu_error:
				onErrorState();
				break;

			case R.id.menu_offline:
				onOfflineState();
				break;
		}

		return true;
	}

	@OnClick(R.id.contentButton) void onShowingContentState()
	{
		setState(State.SHOWING_CONTENT);
	}

	@OnClick(R.id.emptyButton) void onEmptyState()
	{
		setState(State.EMPTY);
	}

	@OnClick(R.id.loadingButton) void onLoadingState()
	{
		setState(State.LOADING);
	}

	@OnClick(R.id.errorButton) void onErrorState()
	{
		setState(State.ERROR);
	}

	@OnClick(R.id.offlineButton) void onOfflineState()
	{
		setState(State.OFFLINE);
	}
}