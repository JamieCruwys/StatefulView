package uk.co.jamiecruwys.contracts;

import android.support.annotation.NonNull;

import uk.co.jamiecruwys.ViewState;

/**
 * Contract for transitioning between states
 */
public interface ViewStateChange
{
	/**
	 * Sets the new view state to transition to
	 *
	 * @param state which it is transitioning to
	 */
	void setViewState(@NonNull ViewState state);

	/**
	 * Gets the current view state
	 *
	 * @return state it is currently in
	 */
	@NonNull ViewState getViewState();
}