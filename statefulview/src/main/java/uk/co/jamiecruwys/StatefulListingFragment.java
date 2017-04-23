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
	private static final String ARG_SCROLL_POSITION = "adapter_position";

	protected RecyclerView recycler;
	protected RecyclerView.Adapter adapter;
	private int scrollPosition = 0;

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = super.onCreateView(inflater, container, savedInstanceState);
		recycler = (RecyclerView)view.findViewById(R.id.recycler);

		if (savedInstanceState != null)
		{
			scrollPosition = savedInstanceState.getInt(ARG_SCROLL_POSITION, 0);
		}

		return view;
	}

	@Override public void onActivityCreated(@Nullable Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		if (savedInstanceState == null)
		{
			refresh(Collections.EMPTY_LIST);
		}
	}

	public void refresh(@NonNull List<ITEM_TYPE> items)
	{
		recycler.setLayoutManager(provideLayoutManager());

		adapter = provideAdapter(items);
		recycler.setAdapter(adapter);

		RecyclerView.ItemDecoration itemDecoration = provideItemDecoration();
		if (itemDecoration != null)
		{
			recycler.addItemDecoration(itemDecoration);
		}

		recycler.scrollToPosition(scrollPosition);
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
		// Do not continue if the fragment is detached from an activity
		if (!isAdded() || getActivity() == null)
		{
			return;
		}

		super.onListingDataRetrieved(items);
		refresh(items);
	}

	@Override public void onSaveInstanceState(Bundle outState)
	{
		LinearLayoutManager layoutManager = (LinearLayoutManager)recycler.getLayoutManager();
		int adapterPosition = layoutManager.findFirstVisibleItemPosition();

		outState.putInt(ARG_SCROLL_POSITION, adapterPosition);
		super.onSaveInstanceState(outState);
	}
}