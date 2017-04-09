package uk.co.jamiecruwys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uk.co.jamiecruwys.contracts.ViewStateChange;
import uk.co.jamiecruwys.contracts.ViewStateLayouts;
import uk.co.jamiecruwys.contracts.ViewStateRootLayout;
import uk.co.jamiecruwys.statefulview.R;

/**
 * A fragment that contains a {@link StatefulView}
 */
public abstract class StatefulFragment extends Fragment implements ViewStateLayouts, ViewStateRootLayout, ViewStateChange
{
	protected StatefulView statefulView;

	/**
	 * Sets up the stateful view inside this fragment
	 */
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(provideLayout(), container, false);
		statefulView = (StatefulView)view.findViewById(provideStatefulViewId());

		statefulView.setStateLayout(getContext(), provideLoadingLayout(), ViewState.LOADING);
		statefulView.setStateLayout(getContext(), provideEmptyLayout(), ViewState.EMPTY);
		statefulView.setStateLayout(getContext(), provideLoadedLayout(), ViewState.LOADED);
		statefulView.setStateLayout(getContext(), provideErrorLayout(), ViewState.ERROR);

		return view;
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
}