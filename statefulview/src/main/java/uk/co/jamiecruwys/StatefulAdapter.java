package uk.co.jamiecruwys;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Jamie Cruwys of 3 SIDED CUBE on 03/04/2017.
 */
public abstract class StatefulAdapter extends RecyclerView.Adapter
{
	private AdapterStateListener stateListener;

	public StatefulAdapter()
	{
		registerAdapterDataObserver(new RecyclerView.AdapterDataObserver()
		{
			@Override public void onChanged()
			{
				super.onChanged();

				if (stateListener == null)
				{
					return;
				}

				if (getItemCount() == 0)
				{
					stateListener.onEmpty();
				}
				else
				{
					stateListener.onFilled();
				}
			}
		});
	}

	public void setStateListener(@NonNull AdapterStateListener stateListener)
	{
		this.stateListener = stateListener;
	}
}