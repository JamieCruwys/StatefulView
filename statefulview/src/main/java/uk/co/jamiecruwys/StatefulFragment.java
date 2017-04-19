package uk.co.jamiecruwys;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import uk.co.jamiecruwys.contracts.InitialViewState;
import uk.co.jamiecruwys.contracts.ListingData;
import uk.co.jamiecruwys.contracts.ViewStateChange;
import uk.co.jamiecruwys.contracts.ViewStateLayouts;
import uk.co.jamiecruwys.contracts.ViewStateRootLayout;
import uk.co.jamiecruwys.statefulview.R;

/**
 * A fragment that contains a {@link StatefulView}
 */
public abstract class StatefulFragment<ITEM_TYPE> extends Fragment implements ViewStateLayouts, ViewStateRootLayout, ViewStateChange, InitialViewState, ListingData<ITEM_TYPE>
{
	protected StatefulView statefulView;

	/**
	 * Sets up the stateful view inside this fragment
	 */
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(provideLayout(), container, false);
		statefulView = (StatefulView)view.findViewById(provideStatefulViewId());

		Context context = statefulView.getContext();

		statefulView.setStateLayout(context, provideLoadingLayout(), ViewState.LOADING);
		statefulView.setStateLayout(context, provideEmptyLayout(), ViewState.EMPTY);
		statefulView.setStateLayout(context, provideLoadedLayout(), ViewState.LOADED);
		statefulView.setStateLayout(context, provideErrorLayout(), ViewState.ERROR);

		return view;
	}

	/**
	 * Provides a default layout for this fragment
	 *
	 * @return default layout resource for this fragment
	 */
	@Override public int provideLayout()
	{
		return R.layout.stateful_view;
	}

	/**
	 * Provides the id of the {@link StatefulView} in the default layout for this fragment
	 *
	 * @return layout id of the {@link StatefulView} in the default layout for this fragment
	 */
	@Override public int provideStatefulViewId()
	{
		return R.id.statefulview;
	}

	/**
	 * Set the initial view state
	 */
	@Override public void onActivityCreated(@Nullable Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		setViewState(provideInitialViewState());
	}

	/**
	 * Sets the new view state to transition to
	 *
	 * @param state which it is transitioning to
	 */
	public void setViewState(@NonNull ViewState state)
	{
		statefulView.setViewState(state);
	}

	@NonNull @Override public ViewState getViewState()
	{
		return statefulView.getViewState();
	}

	@Override public ViewState provideInitialViewState()
	{
		return ViewState.EMPTY;
	}

	@Override public void onResume()
	{
		super.onResume();

		if (shouldReloadOnResume())
		{
			setViewState(ViewState.LOADING);
			getListData(this);
		}
	}

	/**
	 * Whether or not content should be reloaded when the view is resumed
	 *
	 * @return true to reload content on resume, false to not reload content
	 */
	protected boolean shouldReloadOnResume()
	{
		return true;
	}

	/**
	 * Get the data that will be displayed in the list
	 */
	protected abstract void getListData(@NonNull ListingData callback);

	@Override public void onListingDataRetrieved(@NonNull List<ITEM_TYPE> items)
	{
		if (items.isEmpty())
		{
			setViewState(ViewState.EMPTY);
		}
		else
		{
			setViewState(ViewState.LOADED);
		}
	}

	@Override public void onListingDataError(@Nullable Throwable throwable)
	{
		setViewState(ViewState.ERROR);
	}
}