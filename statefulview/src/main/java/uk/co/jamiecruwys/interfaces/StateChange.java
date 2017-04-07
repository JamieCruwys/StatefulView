package uk.co.jamiecruwys.interfaces;

import android.support.annotation.NonNull;

import uk.co.jamiecruwys.State;

/**
 * Created by Jamie Cruwys of 3 SIDED CUBE on 07/04/2017.
 */
public interface StateChange
{
	/**
	 * Sets the state of the view, which in turn updates the layout to be reflective of the new state
	 *
	 * @param state that the view is now in
	 */
	void setState(@NonNull State state);
}
