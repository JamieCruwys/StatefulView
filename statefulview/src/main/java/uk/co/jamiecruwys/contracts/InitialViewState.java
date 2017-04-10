package uk.co.jamiecruwys.contracts;

import uk.co.jamiecruwys.ViewState;

/**
 * Created by Jamie Cruwys of 3 SIDED CUBE on 10/04/2017.
 */
public interface InitialViewState
{
	/**
	 * Provide the initial state that this view should be in
	 *
	 * @return
	 */
	ViewState provideInitialViewState();
}