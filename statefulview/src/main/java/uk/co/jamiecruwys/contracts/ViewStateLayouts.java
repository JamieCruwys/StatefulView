package uk.co.jamiecruwys.contracts;

import android.support.annotation.LayoutRes;

/**
 * Contract for providing layouts for all states
 */
public interface ViewStateLayouts
{
	/**
	 * Provide the layout resource for the LOADING state
	 *
	 * @return layout resource for the LOADING state
	 */
	@LayoutRes int provideLoadingLayout();

	/**
	 * Provide the layout resource for the EMPTY state
	 *
	 * @return layout resource for the EMPTY state
	 */
	@LayoutRes int provideEmptyLayout();

	/**
	 * Provide the layout resource for the LOADED state
	 *
	 * @return layout resource for the LOADED state
	 */
	@LayoutRes int provideLoadedLayout();

	/**
	 * Provide the layout resource for the ERROR state
	 *
	 * @return layout resource for the ERROR state
	 */
	@LayoutRes int provideErrorLayout();
}