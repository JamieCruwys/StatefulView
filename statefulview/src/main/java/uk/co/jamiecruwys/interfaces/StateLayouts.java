package uk.co.jamiecruwys.interfaces;

import android.support.annotation.LayoutRes;

/**
 * Contract for providing layouts for all states
 */
public interface StateLayouts
{
	/**
	 * Provide the layout resource for the SHOWING_CONTENT state
	 * @return layout resource for the SHOWING_CONTENT state
	 */
	@LayoutRes int provideContentLayout();

	/**
	 * Provide the layout resource for the EMPTY state
	 * @return layout resource for the EMPTY state
	 */
	@LayoutRes int provideEmptyLayout();

	/**
	 * Provide the layout resource for the LOADING state
	 * @return layout resource for the LOADING state
	 */
	@LayoutRes int provideLoadingLayout();

	/**
	 * Provide the layout resource for the ERROR state
	 * @return layout resource for the ERROR state
	 */
	@LayoutRes int provideErrorLayout();
}