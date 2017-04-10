package uk.co.jamiecruwys;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import uk.co.jamiecruwys.contracts.ViewStateChange;

/**
 * Created by Jamie Cruwys of 3 SIDED CUBE on 10/04/2017.
 */
public class StatefulDataObserver extends RecyclerView.AdapterDataObserver
{
	@NonNull private RecyclerView.Adapter adapter;
	@NonNull private ViewStateChange callback;

	public StatefulDataObserver(@NonNull RecyclerView.Adapter adapter, @NonNull ViewStateChange callback)
	{
		this.adapter = adapter;
		this.callback = callback;
	}

	@Override public void onChanged()
	{
		super.onChanged();

		if (adapter.getItemCount() == 0)
		{
			callback.setViewState(ViewState.EMPTY);
		}
		else
		{
			callback.setViewState(ViewState.LOADED);
		}
	}
}