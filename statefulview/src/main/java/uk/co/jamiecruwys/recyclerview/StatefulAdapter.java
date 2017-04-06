package uk.co.jamiecruwys.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * Created by Jamie Cruwys of 3 SIDED CUBE on 03/04/2017.
 */
public abstract class StatefulAdapter<VH extends ViewHolder> extends RecyclerView.Adapter<VH>
{
	public void setStateListener(@NonNull final AdapterStateListener stateListener)
	{
		registerAdapterDataObserver(new RecyclerView.AdapterDataObserver()
		{
			@Override public void onChanged()
			{
				super.onChanged();

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
}