package uk.co.jamiecruwys;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Jamie Cruwys of 3 SIDED CUBE on 03/04/2017.
 */
public abstract class StatefulAdapter extends RecyclerView.Adapter
{
	public boolean isEmpty()
	{
		return getItemCount() == 0;
	}
}