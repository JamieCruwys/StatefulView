package uk.co.jamiecruwys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
 * Fragment wrapper for a {@link StatefulView}. To use you must:
 *
 * <ol>
 *     <li>Provide your loading layout using {@link #provideLoadingLayout()}</li>
 *     <li>Provide your empty layout using {@link #provideEmptyLayout()}</li>
 *     <li>Provide your loaded layout using {@link #provideLoadedLayout()} ()}</li>
 *     <li>Provide your error layout using {@link #provideErrorLayout()}</li>
 *     <li>Get your list data in {@link #getListData(ListingData)} and call {@link ListingData}'s interface when the data comes back</li>
 * </ol>
 *
 * Optionally provide:
 *
 * <ul>
 *     <li>Your own root layout by overriding {@link #provideLayout()}. This must contain a {@link StatefulView}. If you implement this method, you must also implement {@link #provideStatefulViewId()}.</li>
 *     <li>If you are implementing {@link #provideLayout()}, then you must implement this to provide the layout id of the {@link StatefulView} that is in your view.</li>
 *     <li>An initial {@link ViewState} by overriding {@link #provideInitialViewState()}. The default is {@link ViewState#EMPTY}.</li>
 *     <li>Whether or not to reload the data when {@link #onResume()} is called. This can be achieved by overriding {@link #shouldReloadOnResume()} and returning true or false.</li>
 * </ul>
 *
 * Methods you can call:
 *
 * <ul>
 *     <li>{@link #setViewState(ViewState)} to change the current {@link ViewState}</li>
 *     <li>{@link #getViewState()} to get the current {@link ViewState}</li>
 * </ul>
 *
 * @param <ITEM_TYPE> the model class that you want to display
 */

// TODO: Rename getlistdata
// TODO: merge providelayout and statefulviewid
// TODO: Refactor + document StatefulFragment, StatefulActivity + contracts

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

		statefulView.setStateLayout(getContext(), provideLoadingLayout(), ViewState.LOADING);
		statefulView.setStateLayout(getContext(), provideEmptyLayout(), ViewState.EMPTY);
		statefulView.setStateLayout(getContext(), provideLoadedLayout(), ViewState.LOADED);
		statefulView.setStateLayout(getContext(), provideErrorLayout(), ViewState.ERROR);

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
		// Do not continue if the fragment is detached from an activity
		if (!isAdded() || getActivity() == null)
		{
			return;
		}

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
		// Do not continue if the fragment is detached from an activity
		if (!isAdded() || getActivity() == null)
		{
			return;
		}

		setViewState(ViewState.ERROR);
	}
}