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

import uk.co.jamiecruwys.statefulview.R;

/**
 * Created by Jamie Cruwys of 3 SIDED CUBE on 10/04/2017.
 */
public abstract class StatefulListingFragment<ITEM_TYPE> extends StatefulFragment<ITEM_TYPE>
{
	protected RecyclerView recycler;
	protected RecyclerView.Adapter adapter;

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
			refresh();
		}
	}

	public void refresh()
	{
		recycler.setLayoutManager(provideLayoutManager());

		adapter = provideAdapter(Collections.EMPTY_LIST);
		recycler.setAdapter(adapter);

		RecyclerView.ItemDecoration itemDecoration = provideItemDecoration();
		if (itemDecoration != null)
		{
			recycler.addItemDecoration(itemDecoration);
		}
	}

	@Override public int provideLoadedLayout()
	{
		return R.layout.stateful_listing;
	}

	/**
	 * Provide the layout manager to use for the listing
	 *
	 * @return {@link android.support.v7.widget.RecyclerView.LayoutManager} to use for the listing
	 */
	@NonNull protected RecyclerView.LayoutManager provideLayoutManager()
	{
		return new LinearLayoutManager(getContext());
	}

	/**
	 * Provide the adapter to use for the listing
	 *
	 * @param items to seed the adapter with
	 *
	 * @return {@link RecyclerView.Adapter} to use for the listing
	 */
	@NonNull protected abstract RecyclerView.Adapter provideAdapter(@NonNull List<ITEM_TYPE> items);

	/**
	 * Provide any item decoration you want applied to the listing
	 *
	 * @return {@link android.support.v7.widget.RecyclerView.ItemDecoration} you want applied to the listing
	 */
	@Nullable protected RecyclerView.ItemDecoration provideItemDecoration()
	{
		return null;
	}

	@Override public void onListingDataRetrieved(@NonNull List<ITEM_TYPE> items)
	{
		super.onListingDataRetrieved(items);

		if (getViewState() == ViewState.LOADED)
		{
			adapter = provideAdapter(items);
			recycler.swapAdapter(adapter, false);
		}
	}
}