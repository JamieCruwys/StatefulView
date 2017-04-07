package uk.co.jamiecruwys.interfaces;

import android.support.annotation.LayoutRes;

/**
 * Created by Jamie Cruwys of 3 SIDED CUBE on 07/04/2017.
 */
public interface StateLayouts
{
	@LayoutRes int provideContentLayout();

	@LayoutRes int provideEmptyLayout();

	@LayoutRes int provideLoadingLayout();

	@LayoutRes int provideErrorLayout();
}