package uk.co.jamiecruwys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import uk.co.jamiecruwys.contracts.ListingData;
import uk.co.jamiecruwys.contracts.ListingSetters;
import uk.co.jamiecruwys.statefulview.R;

/**
 * Created by Jamie Cruwys of 3 SIDED CUBE on 10/04/2017.
 */
public abstract class StatefulListingFragment<ITEM_TYPE> extends StatefulFragment implements ListingData<ITEM_TYPE>
{
	private RecyclerView recycler;
	private RecyclerView.Adapter adapter;
	private StatefulDataObserver dataObserver;

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = super.onCreateView(inflater, container, savedInstanceState);
		recycler = (RecyclerView)view.findViewById(R.id.recycler);
		return view;
	}

	@Override public void onActivityCreated(@Nullable Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		if (savedInstanceState == null)
		{
			recycler.setLayoutManager(provideLayoutManager());

			adapter = provideAdapter(Collections.EMPTY_LIST);
			recycler.setAdapter(adapter);

			RecyclerView.ItemDecoration itemDecoration = provideItemDecoration();
			if (itemDecoration != null)
			{
				recycler.addItemDecoration(itemDecoration);
			}

			dataObserver = new StatefulDataObserver(adapter, this);
		}
	}

	@Override public int provideLoadedLayout()
	{
		return R.layout.stateful_listing;
	}

	/**
	 * Provide the layout manager to use for the listing
	 * @return {@link android.support.v7.widget.RecyclerView.LayoutManager} to use for the listing
	 */
	@NonNull protected RecyclerView.LayoutManager provideLayoutManager()
	{
		return new LinearLayoutManager(getContext());
	}

	/**
	 * Provide the adapter to use for the listing
	 * @param items to seed the adapter with
	 * @return {@link RecyclerView.Adapter} to use for the listing
	 */
	@NonNull protected abstract RecyclerView.Adapter provideAdapter(@NonNull List<ITEM_TYPE> items);

	/**
	 * Provide any item decoration you want applied to the listing
	 * @return {@link android.support.v7.widget.RecyclerView.ItemDecoration} you want applied to the listing
	 */
	@Nullable protected RecyclerView.ItemDecoration provideItemDecoration()
	{
		return null;
	}

	@Override public void onResume()
	{
		super.onResume();

		adapter.registerAdapterDataObserver(dataObserver);

		if (shouldReloadOnResume())
		{
			setViewState(ViewState.LOADING);
			getListData(this);
		}
	}

	/**
	 * Whether or not content should be reloaded when the view is resumed
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
		adapter = provideAdapter(items);
		recycler.swapAdapter(adapter, false);

		setViewState(ViewState.LOADED);
	}

	@Override public void onListingDataError(@Nullable Throwable throwable)
	{
		setViewState(ViewState.ERROR);
	}

	@Override public void onPause()
	{
		super.onPause();
		adapter.unregisterAdapterDataObserver(dataObserver);
	}
}