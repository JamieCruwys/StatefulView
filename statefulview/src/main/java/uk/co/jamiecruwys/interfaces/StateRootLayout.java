package uk.co.jamiecruwys.interfaces;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;

import uk.co.jamiecruwys.StatefulView;

/**
 * Created by Jamie Cruwys of 3 SIDED CUBE on 07/04/2017.
 */
public interface StateRootLayout
{
	/**
	 * Override this if you want to provide your own layout which contains a {@link StatefulView}
	 * @return
	 */
	@LayoutRes int provideLayout();

	/**
	 * Override this if you are providing your own custom layout. If you are then you need to override this and provide the id of the {@link StatefulView}
	 * @return
	 */
	@IdRes int provideStatefulViewId();
}