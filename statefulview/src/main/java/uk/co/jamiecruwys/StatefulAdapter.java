package uk.co.jamiecruwys;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Jamie Cruwys of 3 SIDED CUBE on 03/04/2017.
 */
public abstract class StatefulAdapter extends RecyclerView.Adapter
{
	public StatefulAdapter(@NonNull final StatefulView statefulView)
	{
		registerAdapterDataObserver(new RecyclerView.AdapterDataObserver()
		{
			@Override public void onChanged()
			{
				super.onChanged();

				if (getItemCount() == 0)
				{
					statefulView.setState(State.EMPTY);
				}
				else
				{
					statefulView.setState(State.SHOWING_CONTENT);
				}
			}
		});
	}
}