package uk.co.jamiecruwys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import uk.co.jamiecruwys.contracts.InitialViewState;
import uk.co.jamiecruwys.contracts.ViewStateChange;
import uk.co.jamiecruwys.contracts.ViewStateLayouts;
import uk.co.jamiecruwys.contracts.ViewStateRootLayout;
import uk.co.jamiecruwys.statefulview.R;

/**
 * A activity that contains a {@link StatefulView}
 */
public abstract class StatefulActivity extends AppCompatActivity implements ViewStateLayouts, ViewStateRootLayout, ViewStateChange, InitialViewState
{
	protected StatefulView statefulView;

	/**
	 * Sets up the stateful view inside this activity
	 */
	@Override protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(provideLayout());
		statefulView = (StatefulView)findViewById(provideStatefulViewId());

		statefulView.setStateLayout(this, provideLoadingLayout(), ViewState.LOADING);
		statefulView.setStateLayout(this, provideEmptyLayout(), ViewState.EMPTY);
		statefulView.setStateLayout(this, provideLoadedLayout(), ViewState.LOADED);
		statefulView.setStateLayout(this, provideErrorLayout(), ViewState.ERROR);
	}

	/**
	 * Provides a default layout for this fragment
	 * @return default layout resource for this fragment
	 */
	@Override public int provideLayout()
	{
		return R.layout.stateful_view;
	}

	/**
	 * Provides the id of the {@link StatefulView} in the default layout for this fragment
	 * @return layout id of the {@link StatefulView} in the default layout for this fragment
	 */
	@Override public int provideStatefulViewId()
	{
		return R.id.statefulview;
	}

	/**
	 * Sets the new view state to transition to
	 * @param state which it is transitioning to
	 */
	public void setViewState(@NonNull ViewState state)
	{
		statefulView.setViewState(state);
	}

	@Override public ViewState provideInitialViewState()
	{
		return ViewState.LOADING;
	}
}